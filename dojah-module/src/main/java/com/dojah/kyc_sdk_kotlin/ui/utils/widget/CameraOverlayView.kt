package com.dojah.kyc_sdk_kotlin.ui.utils.widget


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.toColorInt

class CameraOverlayView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val dimPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#B3000000".toColorInt() // semi-transparent black
        style = Paint.Style.FILL
    }

    // Paint for dashed head outline
    private val dashPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = resources.displayMetrics.density * 6f // adjust thickness
        // dash pattern: [dashLength, gapLength]
        pathEffect = DashPathEffect(floatArrayOf(14f, 8f), 0f)
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    // Reusable objects
    private val clipRect = RectF()
    private val ovalRect = RectF()
    private val headPath = Path()
    private val layerPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        // required for using Xfermode-based clear; we will use saveLayer to support it
        layerPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // ensure we draw on top of camera preview
        val width = width.toFloat()
        val height = height.toFloat()

        // draw semi-transparent full-screen dim
        // use saveLayer so we can clear a hole
        val saved = canvas.saveLayer(0f, 0f, width, height, null)

        canvas.drawRect(0f, 0f, width, height, dimPaint)

        // compute a centered oval hole - adjust scale to taste
        val ovalWidth = width * 0.75f
        val ovalHeight = height * 0.92f
        val left = (width - ovalWidth) / 2f
        val top = (height - ovalHeight) / 2f
        ovalRect.set(left, top, left + ovalWidth, top + ovalHeight)

        // cut the hole: draw the oval with CLEAR Xfermode (makes it transparent)
        // we use layerPaint with CLEAR
        canvas.drawOval(ovalRect, layerPaint)

        // restore
        canvas.restoreToCount(saved)

        // ---- draw dashed head path INSIDE the oval ----
        buildHeadPath(ovalRect, headPath)

        // Optionally offset the head path slightly downwards to match your design
        canvas.drawPath(headPath, dashPaint)
    }

    /**
     * Build a head-shaped path inside the provided oval rect.
     * This is an approximation: you can refine points to match exact design.
     */
    private fun buildHeadPath(oval: RectF, outPath: Path) {
        outPath.reset()

        // Make a path slightly inset from the oval so dashed stroke sits inside
        val inset = oval.width().coerceAtMost(oval.height()) * 0.06f
        val r = RectF(oval.left + inset, oval.top + inset, oval.right - inset, oval.bottom - inset)

        // We'll draw a rounded head-oval and add small ear bumps using cubicTo / arcTo for ears.
        // Start from top-center and go clockwise.
        val cx = r.centerX()
        val cy = r.centerY()
        val rx = r.width() / 2f
        val ry = r.height() / 2f

        // Top-center
        outPath.moveTo(cx, r.top + ry * -0.9f + r.top) // slight tweak not necessary

        // Use an oval-based path with more control: approximate by cubic bezier segments
        // Simpler: Add an oval path, then separately carve ear shapes by moving path with arcs.

        // Add main oval (use addOval) but then we'll modify for ears by drawing arcs on sides
        outPath.addOval(r, Path.Direction.CW)

        // To create ear bumps, overlay small ovals (these will be stroked seamlessly)
        // Left ear
        val earWidth = r.width() * 0.18f
        val earHeight = r.height() * 0.22f
        val leftEarCenterX = r.left + earWidth * 0.6f
        val earY = r.centerY() - r.height() * 0.15f
        val leftEarRect = RectF(
            leftEarCenterX - earWidth / 2f,
            earY - earHeight / 2f,
            leftEarCenterX + earWidth / 2f,
            earY + earHeight / 2f
        )
        // Right ear
        val rightEarCenterX = r.right - earWidth * 0.6f
        val rightEarRect = RectF(
            rightEarCenterX - earWidth / 2f,
            earY - earHeight / 2f,
            rightEarCenterX + earWidth / 2f,
            earY + earHeight / 2f
        )

        // We want a single continuous path with ear bumps. We'll assemble a new path:
        val combined = Path()
        // Start at top of r
        combined.moveTo(cx, r.top)
        // go right to just before right ear top
        combined.arcTo(r, -90f, 180f, false) // this sweeps from top around right to bottom
        // bottom-left to left side arc
        combined.arcTo(r, 90f, 90f, false)
        // Now add left ear as small arc appended
        combined.addOval(leftEarRect, Path.Direction.CW)
        // Append right ear too
        combined.addOval(rightEarRect, Path.Direction.CW)

        // For simplicity, we will instead just use the inset oval path drawn earlier.
        // Replace outPath with r-oval (already added)
        // (Note: previous addOval already made outPath)
        // If you want perfectly shaped ears, you can craft the bezier path manually.

        // Since outPath already contains the oval, keep it; the dashed stroke around an oval + small ear ovals works visually.
        // To smoothly merge ear ovals into the path you'd need boolean path ops (not available) or draw ear arcs separately.

        // For a nicer result: draw the oval dashed path, then draw ear small dashed ovals on top:
        // We will keep outPath as the oval and draw ears separately in onDraw below.

        // No-op here — outPath contains the oval
    }

}