# Connect Four Game

An Object-Oriented Programming (OOP) implementation of the classic **Connect Four** two-player board game in Java. The engine manages a standard $6 \times 7$ grid using multi-dimensional arrays, simulates gravity-based disc drops, and verifies winning states across all spatial orientations.

---

## Game Overview
Connect Four is a two-player connection board game where players choose a color/symbol (`X` for Player 1, `O` for Player 2) and take turns dropping tokens into a vertically suspended $6 \times 7$ grid. The objective is to be the first to form a horizontal, vertical, or diagonal line of four consecutive discs.

---

## Structure & Algorithm

### 1. State Management & Grid Simulation
- **2D Array Representation:** Modeled as `int[6][7]`, tracking empty slots (`0`), Player 1 (`1`), and Player 2 (`2`).
- **Gravity-based Disc Drop (`dropDisc`):** Evaluates column boundaries $[1, 7]$ and finds the lowest available row index iteratively (`findFreeRow`).
- **Turn Switch & Game Loop:** Toggles active players via ternary logic (`switchPlayer`) and evaluates board completion to detect draw states (`isBoardFull`).

### 2. Algorithmic Win Verification (`hasWon`)
The engine dynamically checks for four consecutive matching pieces across all possible vectors:
- **Horizontal:** Checks contiguous slots across rows: $[j, j+1, j+2, j+3]$ for $j \in [0, \text{COLS}-4]$.
- **Vertical:** Checks contiguous slots down columns: $[i, i+1, i+2, i+3]$ for $i \in [0, \text{ROWS}-4]$.
- **Major Diagonal ($\searrow$):** Offsets row and column indices simultaneously in positive directions.
- **Minor Diagonal ($\nearrow$):** Evaluates climbing diagonals starting from valid lower boundaries ($i \ge 3$).
