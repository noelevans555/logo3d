package com.noelevans555.logo3d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.noelevans555.logo3d.model.LogoLine;
import com.noelevans555.logo3d.model.LogoPoint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A simple visualizer to show the output of the Logo3d compiler. Three fixed
 * images are produced corresponding to parallel projections of the output lines
 * on the XY, ZY & XZ axis pairs. The axis limits are fixed and so larger images
 * will not be shown in full, and the lines are drawn in the order traversed by
 * the program, i.e. in the projection's perspective, near lines may be occluded
 * by those which are further away.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */
class DemoVisualizer extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final int PANEL_WIDTH = 300;
    private static final int PANEL_HEIGHT = 300;
    private static final int PANEL_HALF_WIDTH = PANEL_WIDTH / 2;
    private static final int PANEL_HALF_HEIGHT = PANEL_HEIGHT / 2;

    private static final int LABEL_SIZE = 15;
    private static final int LABEL_OFFSET_X = 5;
    private static final int LABEL_OFFSET_Y = 18;

    /**
     * The axis projections which are to be displayed.
     */
    @RequiredArgsConstructor
    @Getter
    private enum Projection {
        XY(LogoPoint::getX, LogoPoint::getY),
        ZY(LogoPoint::getZ, LogoPoint::getY),
        XZ(LogoPoint::getX, LogoPoint::getZ);

        private final Function<LogoPoint, Double> horizontalAxis;
        private final Function<LogoPoint, Double> verticalAxis;
    }

    private final List<LogoLine> logoLines;

    /**
     * Constructor.
     *
     * @param logoLines The lines produced by the Logo3d compiler for visualization.
     */
    DemoVisualizer(final List<LogoLine> logoLines) {
        super("Logo3d Demo Visualizer");
        this.logoLines = logoLines;
    }

    /**
     * Activates the display.
     */
    void display() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(PANEL_WIDTH * Projection.values().length, PANEL_HEIGHT);
        setLayout(new GridLayout(1, Projection.values().length));
        for (Projection viewAxis : Projection.values()) {
            add(new ProjectionPane(viewAxis));
        }
        setVisible(true);
    }

    /**
     * Responsible for drawing a single image showing the compiler output projected
     * onto a single axis pair.
     */
    private final class ProjectionPane extends JPanel {

        private static final long serialVersionUID = 1L;

        private final Projection projection;

        /**
         * Constructor.
         *
         * @param projection Axis projection to be used.
         */
        private ProjectionPane(final Projection projection) {
            this.projection = projection;
            setBackground(Color.BLACK);
            setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            setBorder(BorderFactory.createLineBorder(Color.WHITE));
            setFont(new Font("Arial", Font.PLAIN, LABEL_SIZE));
        }

        @Override
        protected void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            graphics.setColor(Color.WHITE);
            graphics.drawString(projection.toString(), LABEL_OFFSET_X, LABEL_OFFSET_Y);
            for (LogoLine logoLine : logoLines) {
                paint(logoLine, graphics);
            }
        }

        /**
         * Draws an individual LogoLine with a given graphics context.
         *
         * @param logoLine Line to draw.
         * @param graphics Graphics context to use.
         */
        private void paint(final LogoLine logoLine, final Graphics graphics) {
            graphics.setColor(logoLine.getColor().toAwtColor());
            graphics.drawLine(PANEL_HALF_WIDTH + projection.getHorizontalAxis().apply(logoLine.getStart()).intValue(),
                    PANEL_HALF_HEIGHT - projection.getVerticalAxis().apply(logoLine.getStart()).intValue(),
                    PANEL_HALF_WIDTH + projection.getHorizontalAxis().apply(logoLine.getEnd()).intValue(),
                    PANEL_HALF_HEIGHT - projection.getVerticalAxis().apply(logoLine.getEnd()).intValue());
        }

    }

}
