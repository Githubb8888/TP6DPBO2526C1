# TP6DPBO2526C1
# Flappy Bird
Tugas Praktikum 6 DPBO 2026

Janji Saya Bozorov Huseyn (NIM: 2521812) mengerjakan evaluasi Tugas Praktikum 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahannya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

A Flappy Bird game built with Java Swing, featuring a main menu, score counter, Game Over, restart, and sound.

## Program Design

A Flappy Bird game built with Java Swing, featuring a main menu, score counter, Game Over, restart, and sound.

The program consists of six classes.

App - the entry point. It creates the window and the main menu with Start and Exit buttons, and switches between the menu and the game using CardLayout.

Player - stores the bird's data: position, size, vertical velocity, and image.

Pipe - stores a pipe's data: position, size, image, and a passed flag (whether the bird has already passed this pipe).

Logic - all of the game logic: timers, gravity, movement, collisions, score, Game Over, restart, and sound.

View - a JPanel that draws the background, the bird, the pipes, and the Game Over text. It also contains the JLabel that shows the score.

Sound - a small wrapper around Clip for playing wav audio files.

Player and Pipe only store data, Logic updates that data, and View draws it.

## Program Flow

1. When the program starts, the main menu is shown. The Exit button closes the program and the Start button opens the game.
2. startGame() puts the bird back at its starting position, clears the list of pipes, resets the score, starts both timers, and starts the background music.
3. The gameLoop timer fires about 60 times per second and calls move(). This method applies gravity to the bird, moves all pipes to the left, checks collisions, adds points, and removes pipes that have left the screen. After that, repaint() is called and View redraws the screen.
4. The pipesCooldown timer fires every 1.5 seconds and calls placePipes(). It creates an upper pipe and a lower pipe at a random height with a gap between them.
5. The Space key makes the bird jump upward.
6. Game Over happens when the bird intersects a pipe (Rectangle.intersects) or touches the bottom border of the window. endGame() is called: the timers and music stop, a hit sound plays, and the GAME OVER text appears on screen.
7. After Game Over, pressing R calls startGame() again and the game restarts.
8. When the bird passes the upper pipe of a pair, that pipe's passed flag becomes true, the score increases by 1, and the JLabel is updated. Only the upper pipe is counted so that a pair gives 1 point instead of 2.

## Controls

Space - jump.
R - restart after Game Over.

## Sound

The game has background music and sound effects for jumping, scoring a point, and hitting a pipe.

## Run Documentation

A video of the program running is in the Dokumentasi folder.
Flappy_bird_1.mov
Flappy_bird_2.mp4
