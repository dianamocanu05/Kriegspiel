# KRIEGSPIEL (BERKELEY)



## TASK

- Implementați o interfață grafică pentru Kregspiel (varianta Berkeley).
- Interfața permite vizualizarea tututor stărilor și mesajelor anterioare.
- Interfața permite adăugarea de notițe atât generice cât și asociate cu o poziție de pe tabla de joc.
- AI-ul folosește strategia Monte-Carlo Tree Search.
- Legături relevante: 
  
  			1. http://w01fe.com/berkeley/kriegspiel/rules.html 
    		2. https://www.aaai.org/Papers/Symposia/Spring/2007/SS-07-02/SS07-02-003.pdf
    		3. https://www.ics.uci.edu/~dechter/courses/ics-295/fall-2019/papers/2010-mtc-aij.pdf
    		4. https://github.com/Kriegspiel/ks-game

## IDEAS

- Implementing Human v  Computer mode
- Implementing Computer v Computer mode, with modelling famous battles

## SPECIFICATIONS

- Java Swing for the UI
- Monte-Carlo Tree Search Algorithm for AI 

## DOCUMENTATION

### Monte Carlo Tree Search

```
	"Monte Carlo tree search (MCTS) is an evolution of some simpler and older methods based on Monte Carlo sampling.
While the core concept is still the same – a program plays a large number of random simulated games and picks the move
that seems to yield the highest victory ratio – the purpose of MCTS is to make the computation converge to the right value
much more quickly than pure Monte Carlo. This is accomplished by guiding the simulations with a game tree that grows to
accommodate new nodes over time; more promising nodes are, in theory, reached first and visited more often than nodes
that are likely to be unattractive.
	MCTS is an iterative method that performs the same four steps until its available time runs out. These steps are summarized in Fig. 1.
	• Selection. The algorithm selects a leaf node from the tree based on the number of visits and their average value.
	• Expansion. The algorithm optionally adds new nodes to the tree.
	• Simulation. The algorithm somehow simulates the rest of the game one or more times, and returns the value of the
final state (or their average, if simulated multiple times).
	• Backpropagation. The value is propagated to the node’s ancestors up to the root, and new average values are computed
for these nodes"

[https://www.ics.uci.edu/~dechter/courses/ics-295/fall-2019/papers/2010-mtc-aij.pdf]
```

### Kriegspiel Berkeley Variant Rules

1. **Players**: each have a chess table, not having to access to the adversary's pieces position

2. **Referee**: 

   2.1 has access to the complete chess table

   2.2 can only say : 	

   - **Yes** (if move is legal) 

   		- **No** (move is not legal)
   		- **Nonsense** is the move is illegal on the current player's board or the player insists to make it (not recorded as part of the move history)
   		- **Capture at position**
   		- **Check**
   		- **Checkmate** or **Stalement** if not legal moves can be done or in check (ends the game)

3. **Piece movement** are identical to the **chess rules**