# Dojah Kotlin SDK Unit Testing Plan

## Executive Summary

The SDK module currently contains approximately:

- 21,600 lines of Kotlin
- 509 declared functions
- A 1,613-line `GovDataViewModel`
- A 1,144-line `VerificationViewModel`
- A repository with approximately 30 API operations
- Nine passing JVM tests

The existing tests are primarily exploratory examples that print dates, phone numbers, and formatting results. They contain few meaningful assertions and do not currently protect production behavior. For planning purposes, effective business-logic coverage is close to zero.

The current unit-test task builds successfully:

```text
:dojah-module:testMobileDebugUnitTest
9 tests, 0 failures
```

This report assumes that **one Android engineer** will design, implement, review, stabilize, and document the tests. No parallel engineering capacity is assumed.

The recommended delivery target is:

- **6 weeks** for the critical JVM unit-test suite
- **8 weeks** for the critical unit-test suite plus selected Android-facing integration tests

## Objectives

The testing initiative should:

1. Protect authentication and KYC verification behavior.
2. Verify network-response and error classification.
3. Protect OTP, government ID, business ID, address, document, and liveness flows.
4. Verify encryption, persistence, navigation, and configuration logic.
5. Establish reliable test execution and coverage reporting in CI.
6. Reduce regression risk without creating low-value tests for trivial code.

## Recommended Test Scope

### Priority 0: Test Infrastructure

Before adding business tests, the module needs a dependable testing foundation:

- Coroutine test dispatchers and test rules
- LiveData testing support
- A mocking framework
- Mock HTTP server support or Retrofit service fakes
- Fixture builders for large API models
- JaCoCo or Kover coverage reporting
- CI execution for every pull request
- Replacement or removal of the current sample tests

Most important SDK logic is asynchronous and coupled to repositories, Android context, preferences, and LiveData. This foundation is therefore required before meaningful coverage can be implemented efficiently.

**Estimated effort:** 2–3 engineer-days

---

## Priority 1: Critical Business and Security Logic

These tests should be completed first because failures can affect verification results, customer access, security, or data integrity.

### 1. API Response and Error Mapping

Primary file:

```text
data/repository/base/BaseRepository.kt
```

Functions and logic requiring tests:

- `checkNetworkAndStartRequest`
  - Connected and successful request
  - Disconnected device
  - DNS failure
  - Socket timeout
  - Unexpected exception
- `getResult`
  - Valid success body
  - Body containing an `error` key
  - HTTP error body
  - Empty response body and empty error body
  - Malformed JSON
  - Nested API errors
  - HTTP status-code preservation
- `stringToType`
- `Result.toJson`

This is the common result-conversion path used across the SDK. Incorrect classification could show false successes, hide server errors, or produce misleading generic failures.

**Estimated effort:** 2–3 days

### 2. Repository API Operations

Primary file:

```text
data/repository/DojahRepository.kt
```

Functions requiring tests:

- `doPreAuth`
- `doAuth`
- `saveAuthDetailsToPrefs`
- `getUserIp`
- `checkUserIp`
- `logEvent`
- `logQuestionEvent`
- `lookUpBvn`
- `lookUpNin`
- `lookUpVnin`
- `lookUpDriverLicense`
- `lookupCac`
- `lookupTin`
- `sendOtp`
- `validateOtp`
- `performImageAnalysis`
- `performDocImageAnalysis`
- `checkLiveness`
- `verifyLiveness`
- `sendUserData`
- `sendBaseAddress`
- `sendAddress`
- `uploadAdditionalFile`
- `sendMetadata`
- `makeFinalDecision`
- `getLocalResponse`
- `deleteAllAuthData`

Each operation should cover:

- Correct endpoint parameters and request body
- Success conversion
- API error conversion
- Network and timeout errors
- Preference or cache side effects
- Missing or invalid response data

**Estimated effort:** 5–7 days

### 3. Authentication and Verification Orchestration

Primary file:

```text
ui/main/viewmodel/VerificationViewModel.kt
```

Functions and logic requiring tests:

- `authenticate`
  - Pre-auth and authentication success
  - Invalid configuration
  - IP-check branches
  - Metadata submission
  - Preference updates
  - Loading, success, and error emissions
- `sendMetadata`
- `sendUserData`
- `sendAddress`
  - Verification enabled and disabled
  - Address within and outside the allowed location range
  - Base-address submission failure
  - Address-verification failure
  - Event-logging failure
- `sendSignatureData`
- `sendCustomQuestionAnswer`
- `selectCountryIfJustOne`
- Country and state loading and filtering
- Building-photo state management
- Document URI assignment and replacement

**Estimated effort:** 5–7 days

### 4. Government and Business Verification Flows

Primary file:

```text
ui/main/viewmodel/GovDataViewModel.kt
```

Functions and logic requiring tests:

- Verification type and identity selection
- `getGovIdTypes`
- `getVerifyMethods`
- `getDocIDTypes`
- `getBusinessTypes`
- `getCompanyTypes`
- `submitGovDataForm`
  - BVN branch
  - NIN branch
  - Virtual NIN branch
  - Driver's licence branch
  - Required-field validation
  - Success and failure event logging
- OTP operations:
  - `sendOtp`
  - `sendOtpSync`
  - `validateGovDataPhoneOtp`
  - `validateEmailOtp`
  - `validatePhoneOtp`
  - `collectEmailWithoutOtp`
  - `collectPhoneNumber`
- Image and document analysis
- Utility bill validation
- Live-location image handling
- Liveness checks and verification
- Additional-document submission
- Business-data submission
- Signature submission
- Automatic government-ID submission
- Server enum and document-type mapping

This class contains the largest concentration of conditional KYC behavior and therefore represents the largest testing workstream.

**Estimated effort:** 8–10 days

### 5. Error and Failure-Code Mapping

Primary files:

```text
ui/main/viewmodel/VerificationViewModel.kt
ui/utils/DojahEnums.kt
```

Functions and logic requiring tests:

- `getErrorMessage`
- `getFailureCode`
- `FailedReasons.getStatusCodeReason`
- `FailedReasons.getGovBizMsg`
- HTTP 402, 404, 424, and 500–599 mappings
- Network, timeout, and no-data messages
- Nested server error extraction
- Missing, empty, and malformed error objects
- Government versus business error wording

This area directly controls customer-facing messages such as:

```text
An error occurred. Try again later
```

**Estimated effort:** 1–2 days

### 6. Encryption and Security

Primary files:

```text
core/util/AesEncryption.kt
data/security/SecurityManager.kt
```

Functions requiring tests:

- `AesEncryption.encrypt`
- `String.encrypted`
- `SecurityManager.encrypt`
- `SecurityManager.decrypt`
- Key derivation behavior

Required cases:

- Encryption/decryption round trip
- Known deterministic test vectors where applicable
- Empty and Unicode input
- Invalid ciphertext
- Incorrect key or salt
- Key-length handling
- Compatibility with previously encrypted values
- Verification that sensitive data and secrets are not logged

**Estimated effort:** 2 days

---

## Priority 2: High-Value Supporting Logic

### 1. Location Verification

Primary file:

```text
data/LocationManager.kt
```

Functions requiring tests:

- `distanceBetween`
- `withinRange`

Required cases:

- Identical coordinates
- Known geographic distances
- Exactly on the configured threshold
- Just inside and outside the threshold
- Negative coordinates
- Antimeridian inputs
- Near-pole inputs

`distanceBetween` returns metres, while `withinRange` calculates kilometres and defaults to `0.05`, meaning 50 metres. Tests should explicitly protect this unit conversion.

**Estimated effort:** 1 day

### 2. Navigation State

Primary file:

```text
ui/base/NavigationViewModel.kt
```

Functions and logic requiring tests:

- Current and previous page-index persistence
- `navigate`
- `navigateNextStep`
- Events being consumed only once
- Route-stack push and pop behavior
- Empty-stack behavior
- `makeFinalDecision`
- Resume-verification branches:
  - Continue an existing verification
  - Duplicate reference with pending result
  - Duplicate reference with completed result
  - Normal next-step behavior
- `updateCurrentSteps`

**Estimated effort:** 2–3 days

### 3. Preferences and Cached State

Primary file:

```text
data/io/SharedPreferenceManager.kt
```

Logic requiring tests:

- Important set/get pairs
- Location precision
- Authentication and session persistence
- Extra-user-data serialization
- Authentication history updates and duplicate handling
- Saved API-response serialization and deserialization
- Temporary preference cleanup
- Preference-change callbacks
- Missing or corrupt stored JSON

**Estimated effort:** 2–3 days

### 4. Country and Configuration Logic

Primary files:

```text
data/io/CountryManager.kt
ui/utils/DojahEnums.kt
ui/main/viewmodel/VerificationViewModel.kt
```

Logic requiring tests:

- Country JSON parsing
- State lookup
- City filtering
- Country asset and image matching
- Missing or malformed asset data
- Single-country automatic selection
- Page lookup and ordering
- `VerificationMethod.fromString`
- `VerificationType.enumOfValue`
- `VerificationType.findEnumWithKey`
- `GovDocType` mappings
- `KycPages.findPageEnum`

**Estimated effort:** 2 days

### 5. Email and Input Rules

Functions and logic requiring tests:

- `isDisposableMail`
- `isFreeMail`
- Null and malformed email values
- Mixed-case email values
- Subdomains
- Leading and trailing whitespace
- Disposable-provider configuration enabled and disabled
- Free-provider configuration enabled and disabled
- String normalization
- Title case
- Initial generation
- Date formatting
- Phone-number parsing and normalization

**Estimated effort:** 1–2 days

### 6. Pricing Logic

Primary file:

```text
core/util/DojahPricingUtil.kt
```

Test `getPricingServices` for:

- Matching services
- Unknown services
- Empty pricing or configuration
- Duplicate services
- Missing prices
- Correct service selection and totals

**Estimated effort:** 1 day

### 7. Timers and Event Wrappers

Primary files:

```text
core/Event.kt
core/util/CancellableCountDownTimer.kt
```

Functions and logic requiring tests:

- `Event.getContentIfNotHandled`
- `Event.peekContent`
- Countdown start
- Countdown tick
- Countdown finish
- Countdown restart
- Countdown cancellation
- OTP resend timer state

**Estimated effort:** 1 day

---

## Priority 3: Android-Facing Tests

The following areas are better suited to Robolectric or instrumentation tests than pure JVM unit tests:

- URI and file handling
- Camera output URI creation
- SharedPreferences integration
- Header-interceptor construction
- App-bar and custom input widgets
- OTP widget behavior
- Fragment validation and button enablement
- Permission-result handling
- Navigation graph routing
- Loading and error visibility
- Activity result codes
- Document capture
- Selfie capture
- Utility bill capture
- Building-photo capture

**Estimated effort:** 7–10 days after the core unit suite

## Functions That Do Not Require Individual Unit Tests

Testing every declared function would increase maintenance cost without providing proportional value. Individual tests are generally unnecessary for:

- Plain data classes
- Simple property getters and setters
- Retrofit interface declarations
- Generated ViewBinding code
- Resource-only behavior
- RecyclerView binding without transformations
- One-line wrappers already exercised through higher-level flow tests

## Single-Engineer Delivery Timeline

The following is a favorable but achievable timeline for one Android engineer working primarily on this initiative. It assumes stable requirements, timely access to API contracts, and no major production interruption.

| Week | Areas | Planned outcome |
|---|---|---|
| Week 1 | Test infrastructure and `BaseRepository` | Test libraries, coroutine rules, mocks/fakes, fixtures, coverage reporting, CI setup, and response/error-mapping tests |
| Week 2 | `DojahRepository` | Authentication, OTP, lookup, address, metadata, image-analysis, liveness, upload, decision, caching, and network-error tests |
| Week 3 | `VerificationViewModel` | Authentication orchestration, user submission, address verification, signature, custom questions, country/page selection, and state-management tests |
| Week 4 | `GovDataViewModel`, part 1 | Verification-type selection, government ID types, BVN/NIN/vNIN/driver's licence branches, validation, and OTP behavior |
| Week 5 | `GovDataViewModel`, part 2 | Business verification, image/document analysis, utility bill, live-location images, liveness, additional documents, signatures, and event logging |
| Week 6 | Supporting critical logic and stabilization | Error/failure mapping, security, location calculations, navigation, preferences, country/configuration, email rules, pricing, timers, coverage review, and flaky-test correction |
| Week 7 | Android-facing integration tests, part 1 | Preferences, files and URIs, header interceptor, fragment validation, permission results, loading/error states, and navigation routing |
| Week 8 | Android-facing integration tests, part 2 and handover | OTP/custom widgets, document/selfie/building capture paths, activity results, final regression run, documentation, and stakeholder report |

### Delivery Milestones

- **End of Week 1:** Automated test foundation and CI execution are available.
- **End of Week 3:** Repository and main verification orchestration have meaningful protection.
- **End of Week 5:** Core government, business, OTP, document, and liveness logic is covered.
- **End of Week 6:** Critical JVM unit-test scope is complete.
- **End of Week 8:** Selected Android integration coverage, stabilization, and handover are complete.

### Scope Commitment

- **Primary commitment:** Complete the critical unit-test scope by the end of Week 6.
- **Extended commitment:** Complete selected Android-facing integration tests by the end of Week 8.
- UI rendering, full end-to-end backend tests, device-matrix testing, and exhaustive visual regression testing are outside this unit-testing estimate.

## Recommended Stakeholder Commitment

> As the sole engineer assigned to this initiative, I will establish automated coverage for the SDK's critical authentication, API-response, verification, OTP, address, government ID, business ID, security, persistence, and navigation logic within six weeks. In weeks seven and eight, I will add selected Android-specific integration coverage, stabilize the suite, and provide the final coverage and risk report.

## Suggested Success Criteria

- All Priority 1 logic is covered.
- Repositories, ViewModels, and pure utilities achieve at least 70% line coverage.
- Error mapping, address verification, and OTP flows achieve at least 80% branch coverage.
- The test suite runs on every pull request.
- Releases are blocked when critical tests fail.
- Test fixtures do not contain real customer or production credentials.
- Tests are deterministic and do not depend on live backend services.
- Flaky tests are tracked and corrected rather than silently retried indefinitely.
- Coverage is treated as a risk indicator, not the sole quality metric.

## Risks and Dependencies

- The large ViewModels have several responsibilities and may require small refactors or dependency injection improvements before they can be tested reliably.
- Android framework dependencies may require Robolectric or wrapper abstractions.
- Large response models will require reusable fixture builders.
- Network tests should use mocks or fakes and must not call production APIs.
- Existing hardcoded secrets and API configuration should be reviewed separately from the testing initiative.
- The timeline assumes stable product requirements during implementation.
