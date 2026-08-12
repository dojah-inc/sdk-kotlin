# Dojah Kotlin SDK — Unit Testing Progress Report

**Report date:** 11 August 2026  
**Module:** `dojah-module`  
**Test task:** `:dojah-module:testMobileDebugUnitTest`  
**Coverage tool:** Kover (`:dojah-module:koverXmlReportMobileDebug`)

---

## Executive Summary

The unit testing initiative has moved from **9 exploratory tests with near-zero meaningful coverage** to a **179-test suite with 0 failures**, CI integration, and structured coverage across Priority 1 layers plus Week 5–6 utilities.

| Metric | Baseline (plan) | Current |
|--------|-----------------|---------|
| Tests | 9 | **179** |
| Failures | 0 | **0** |
| Module line coverage (8 Aug) | ~0% effective | **22.1%** (2,142 / 9,709 lines) — re-run Kover to refresh |
| Module instruction coverage (8 Aug) | — | **24.0%** (15,500 / 64,596 instructions) |
| CI | None | **GitHub Actions** on PR/push |

**Timeline vs plan:** Weeks **1–5 complete**, Week **6 largely complete** (pricing, timer, StringExt), Weeks **7–8 not started**.

---

## Timeline Progress (8-Week Plan)

| Week | Planned Scope | Status | Notes |
|------|---------------|--------|-------|
| **1** | Infrastructure + `BaseRepository` | **Complete** | MockK, coroutines-test, MockWebServer, Robolectric, Kover, test utilities, CI |
| **2** | `DojahRepository` | **Complete** | 35 tests (auth + KYC flows) |
| **3** | `VerificationViewModel` | **Complete** | Auth, submission, state, signature, custom questions, logEvent, timer smoke |
| **4** | `GovDataViewModel` part 1 | **Complete** | Config, gov submission branches, OTP |
| **5** | `GovDataViewModel` part 2 | **Complete** | Image/doc analysis, business, signature, liveness, utility bill, live location, additional docs |
| **6** | Supporting logic + stabilization | **Mostly complete** | Pricing util, countdown timer, StringExt; navigation/preferences still open |
| **7–8** | Android integration tests | **Not started** | Fragments, activities, capture flows |

---

## Test Suite Breakdown (179 Tests)

| Test Class | Tests | Primary Target |
|------------|------:|----------------|
| `DojahRepositoryKycTest` | 23 | `DojahRepository` (lookups, OTP, address, liveness, uploads) |
| `BaseRepositoryTest` | 15 | `BaseRepository` |
| `GovDataViewModelConfigTest` | 13 | `GovDataViewModel` (selection/config) |
| `StringExtTest` | 13 | `StringExt` utilities |
| `DojahRepositoryAuthTest` | 12 | `DojahRepository` (auth, IP, metadata) |
| `VerificationViewModelStateTest` | 12 | `VerificationViewModel` (state/URI/country) |
| `ErrorMappingTest` | 10 | `VerificationViewModel` error helpers |
| `GovDataViewModelLivenessTest` | 9 | `GovDataViewModel` (liveness, docs, location, uploads) |
| `GovDataViewModelOtpTest` | 8 | `GovDataViewModel` (OTP flows) |
| `DojahPricingUtilTest` | 8 | `DojahPricingUtil` |
| `FailedReasonsTest` | 7 | `FailedReasons` enum |
| `GovDataViewModelSubmissionTest` | 6 | `GovDataViewModel` (BVN/NIN/vNIN/DL) |
| `AesEncryptionTest` | 6 | `AesEncryption` |
| `VerificationViewModelSubmissionTest` | 6 | `VerificationViewModel` (user data, address) |
| `LocationManagerTest` | 6 | `LocationManager.Companion` (distance/range) |
| `GovDataViewModelExtendedTest` | 5 | `GovDataViewModel` (image, business, signature) |
| `VerificationViewModelExtendedTest` | 5 | `VerificationViewModel` (signature, questions, logEvent) |
| `SecurityManagerTest` | 4 | `SecurityManager` |
| `EventTest` | 4 | `Event` wrapper |
| `VerificationViewModelAuthTest` | 4 | `VerificationViewModel` (authenticate flow) |
| `CancellableCountDownTimerTest` | 3 | `CancellableCountDownTimer` |

---

## Code Coverage by Tested Class

Coverage sourced from Kover report: `dojah-module/build/reports/kover/reportMobileDebug.xml`  
Metrics: **line %**, **instruction %**, **branch %** (where applicable).

### Priority 1 — Repository & Network Layer

| Production Class | Test Class(es) | Line | Instruction | Branch | Lines Covered |
|------------------|----------------|-----:|------------:|-------:|--------------:|
| `BaseRepository` | `BaseRepositoryTest` | **100.0%** | **100.0%** | **100.0%** | 33 / 33 |
| `DojahRepository` | `DojahRepositoryAuthTest`, `DojahRepositoryKycTest` | **89.0%** | **86.9%** | **58.3%** | 73 / 82 |

**Gap:** Branch coverage is below the 80% plan target; some repository paths (advanced error branches, untested API methods) remain uncovered.

### Priority 1 — Security & Encryption

| Production Class | Test Class(es) | Line | Instruction | Branch | Lines Covered |
|------------------|----------------|-----:|------------:|-------:|--------------:|
| `SecurityManager` | `SecurityManagerTest` | **100.0%** | **100.0%** | — | 16 / 16 |
| `AesEncryption` | `AesEncryptionTest` | **100.0%** | **100.0%** | — | 11 / 11 |
| `AesEncryptionKt` (`String.encrypted`) | — | **0.0%** | **0.0%** | — | 0 / 1 |

**Production fix during testing:** `SecurityManager` PBKDF2 salt mismatch between encrypt/decrypt was fixed (shared `SALT` constant).

### Priority 1 — ViewModels

| Production Class | Test Class(es) | Line | Instruction | Branch | Lines Covered |
|------------------|----------------|-----:|------------:|-------:|--------------:|
| `VerificationViewModel` | Auth, Submission, State, `ErrorMappingTest` | **59.4%** | **56.1%** | **35.2%** | 236 / 397 |
| `GovDataViewModel` | Config, Submission, OTP, Extended | **66.6%** | **67.7%** | **41.9%** | 217 / 326 |

#### VerificationViewModel — Covered

- `authenticate()` (success, pre-auth failure, 402 resume, metadata)
- `sendUserData()`, `sendAddress()` (verification on/off, geo mismatch, errors)
- State: country, doc types, cities, document URIs, selfies, building photos, events
- `getErrorMessage()`, `getFailureCode()` (via `ErrorMappingTest`)
- Email/phone disposable checks

#### VerificationViewModel — Not Yet Covered

- `logEvent()`, `logCountryEvents()`, custom questions, signature submission
- OTP timer, navigation helpers, most document-upload paths

#### GovDataViewModel — Covered

- Verification type / identity selection, config getters
- `submitGovDataForm()` — BVN, NIN, vNIN, DL, lookup failure, OTP trigger
- OTP send/validate (gov, email, phone), `collectEmailWithoutOtp`
- `performImageAnalysis()`, `submitBusinessData()` (CAC), `submitSignature()`

#### GovDataViewModel — Not Yet Covered

- `checkLiveness()`, `checkUtilityBillImage()`, `checkLiveLocationImages()`
- `performDocImageAnalysis()`, `doCheckForDocId()`, `sendAdditionalDoc()`
- `logIdOptionEvents()`, `autoSendGovIdDetails()`, `downloadImageAndConvertToBase64()`

### Priority 1 — Error Mapping & Utilities

| Production Class | Test Class(es) | Line | Instruction | Branch | Lines Covered |
|------------------|----------------|-----:|------------:|-------:|--------------:|
| `FailedReasons` | `FailedReasonsTest` | **96.8%** | **97.0%** | **75.0%** | 30 / 31 |
| `FailedReasons.Companion` | `FailedReasonsTest` | **100.0%** | **90.7%** | **85.7%** | 6 / 6 |
| `StringExtKt` | `StringExtTest` | **36.7%** | **58.9%** | **26.3%** | 22 / 60 |
| `Event` | `EventTest` | **100.0%** | **100.0%** | **100.0%** | 7 / 7 |

`ErrorMappingTest` exercises methods on `VerificationViewModel`; their coverage is included in the ViewModel row above.

### Priority 2 — Location

| Production Class | Test Class(es) | Line | Instruction | Branch | Lines Covered |
|------------------|----------------|-----:|------------:|-------:|--------------:|
| `LocationManager.Companion` | `LocationManagerTest` | **100.0%** | **100.0%** | **100.0%** | 39 / 39 |
| `LocationManager` (instance / GPS) | — | **0.0%** | **0.0%** | — | 0 / 35 |

Tests cover pure distance/range math only; Android GPS fetching is untested (planned for Week 6 / integration).

### Indirectly Exercised Domain Models

| Class | Line Coverage | How Exercised |
|-------|-------------:|---------------|
| `Config` | **91.2%** (83/91) | ViewModel config getters (`govIds`, `verificationMethods`, etc.) |
| `PreAuthResponse` | **90.8%** (109/120) | Auth flow stubs and repository tests |
| `AuthResponse` | **12.1%** (7/58) | Mostly fixture deserialization; merge/update logic untested |
| `DojahPricingUtil` | **75.5%** (37/49) | Event logging in gov/verification flows |

---

## Infrastructure Delivered

| Component | Status |
|-----------|--------|
| `MainDispatcherRule`, `InstantTaskExecutorRule` | Done |
| `LiveDataTestUtil` + `ResultTestUtil` | Done |
| `RepositoryTestSupport`, `VerificationViewModelTestSupport`, `GovDataViewModelTestSupport` | Done |
| MockK + kotlinx-coroutines-test + MockWebServer + Robolectric | Done |
| Kover coverage reporting | Done |
| CI workflow (`.github/workflows/unit-tests.yml`) | Done — runs tests + uploads Kover XML |

### Test Utilities

```
src/test/java/com/dojah/kyc_sdk_kotlin/testutil/
├── MainDispatcherRule.kt
├── TestBaseRepository.kt
├── ResponseFixtures.kt
├── LiveDataTestUtil.kt
├── RepositoryTestSupport.kt
├── VerificationViewModelTestSupport.kt
└── GovDataViewModelTestSupport.kt
```

---

## Success Criteria Gap Analysis

| Criterion | Target | Current |
|-----------|--------|---------|
| Priority 1 logic covered | All | **~75% complete** — core repo/security done; ViewModels partial |
| Repositories / ViewModels / utilities ≥ 70% line | 70% | **Met:** BaseRepository, SecurityManager, AesEncryption, FailedReasons, LocationManager.Companion, DojahRepository. **Partial:** VerificationViewModel (59%), GovDataViewModel (67%), StringExt (37%) |
| Error mapping / OTP / address ≥ 80% branch | 80% | **FailedReasons ~75–86%** close; **VerificationViewModel branches 35%**, **GovDataViewModel branches 42%** — below target |
| CI on every PR | Yes | **Yes** |
| No live backend dependency | Yes | **Yes** (MockWebServer + mocked repos) |

---

## Recommended Next Steps

1. **Week 6 remainder** — Navigation helpers, SharedPreferenceManager critical paths, remaining ViewModel edge cases (`doCheckForDocId`, `logIdOptionEvents`, `autoSendGovIdDetails`)
2. **Refresh Kover report** — Re-run coverage after this batch to update class-level percentages
3. **Weeks 7–8** — Robolectric/instrumentation for fragments and capture flows

---

## How to Reproduce

```bash
# Run all unit tests
./gradlew :dojah-module:testMobileDebugUnitTest

# Generate coverage report
./gradlew :dojah-module:koverXmlReportMobileDebug

# Report locations
# dojah-module/build/reports/kover/reportMobileDebug.xml
# dojah-module/build/test-results/testMobileDebugUnitTest/
```

---

## Bottom Line

The SDK now has **179 passing tests** covering repositories, security, GovData/Verification ViewModels (including liveness and document flows), pricing, timers, and string utilities. Weeks 1–6 of the unit-test plan are essentially complete for JVM logic. Remaining work is navigation/preferences polish and Weeks 7–8 Android-facing integration tests.