package com.noelevans555.logo3d;

/**
 * Demonstration programs which can be run with the Logo3d compiler.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */
final class DemoPrograms {

    static final String GRID_SPHERE = String.join("\n",
            " ; reverse to starting position on sphere surface            ",
            " penup                                                       ",
            " reverse 110                                                 ",
            " pendown                                                     ",
            "                                                             ",
            " ; turn so that forward movement is 'on' the sphere surface  ",
            " turnup 90                                                   ",
            "                                                             ",
            " ; repeat for 20 randomly selected colors                    ",
            " repeat 20 [                                                 ",
            "                                                             ",
            "     ; randomly select a color                               ",
            "     setcolor pick [                                         ",
            "         red green blue yellow                               ",
            "         cyan magenta orange purple                          ",
            "     ]                                                       ",
            "                                                             ",
            "     ; draw 500 lines on sphere surface in selected color    ",
            "     repeat 500 [                                            ",
            "                                                             ",
            "         ; draw forward line                                 ",
            "         forward 10                                          ",
            "                                                             ",
            "         ; turn down to track sphere surface. To stay on     ",
            "         ; the sphere surface, next left/right turn must be  ",
            "         ; performed halfway through this downward turn      ",
            "         turndown 2.5                                        ",
            "                                                             ",
            "         ; randomly turn left or right by 90 degrees         ",
            "         turnleft pick [ 90 270 ]                            ",
            "                                                             ",
            "         ; complete the downward turn                        ",
            "         turndown 2.5                                        ",
            "     ]                                                       ",
            " ]                                                           ");

    static final String RECURSIVE_TREE = String.join("\n",
            " ; define the recursive tree procedure with parameters:      ",
            " ;   size = length of the next 'branch'                      ",
            " ;   angle = tilt of the next 'branch'                       ",
            " ;   color = color of the next 'branch'                      ",
            " ;   color_step = size of color change for each 'branch'     ",
            " to tree size angle color color_step [                       ",
            "                                                             ",
            "     ; draw the branch in the specified color                ",
            "     setcolor color                                          ",
            "     forward size * 5                                        ",
            "                                                             ",
            "     ; dont draw next branch if size is small                ",
            "     if size < 0.8 [ stop ]                                  ",
            "                                                             ",
            "     ; pivot on forward axis and mark branching location     ",
            "     rollleft 90                                             ",
            "     mark local branch_point                                 ",
            "                                                             ",
            "     ; draw the left branch via recursive call               ",
            "     turnleft angle                                          ",
            "     tree size * 0.8 angle + 2 color + color_step color_step ",
            "                                                             ",
            "     ; return to the branching location                      ",
            "     goto branch_point                                       ",
            "                                                             ",
            "     ; draw the right branch via recursive call              ",
            "     turnright angle                                         ",
            "     tree size * 0.8 angle + 2 color + color_step color_step ",
            " ]                                                           ",
            "                                                             ",
            " ; reverse to the start position                             ",
            " penup                                                       ",
            " reverse 100                                                 ",
            " pendown                                                     ",
            "                                                             ",
            " ; run the procedure with starting values                    ",
            " tree 10 20 [130 50 0] [ -10 20 10 ]                         ");

    /**
     * Private constructor to prevent instantiation.
     */
    private DemoPrograms() {
    }

}
