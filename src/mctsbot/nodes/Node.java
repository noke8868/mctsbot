package mctsbot.nodes;

import java.util.List;

import mctsbot.gamestate.GameState;
import mctsbot.strategies.StrategyConfiguration;

public abstract class Node {
	
	private double expectedValue;
	private int visitCount;
	private final Node parent;
	
	protected final GameState gameState;
	protected List<Node> children;
	
	protected final StrategyConfiguration config;
	
	public Node(Node parent, GameState gameState, StrategyConfiguration config) {
		this.parent = parent;
		this.gameState = gameState;
		this.config = config;
		
		this.expectedValue = 0.0;
		this.visitCount = 0;
		
		this.children = null;
	}

	public void setExpectedValue(double expectedValue) {
		this.expectedValue = expectedValue;
	}

	public double getExpectedValue() {
		return expectedValue;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	
	public int getVisitCount() {
		return visitCount;
	}

	public Node getParent() {
		return parent;
	}

	public List<Node> getChildren() {
		return children;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public boolean isExpanded() {
		return children!=null;
	}
	
	public Node select() {
		System.out.println("select is being called");
		return config.getSelectionStrategy().select(this);
	}
	
	public void backpropagate(double expectedValue) {
		System.out.println("backpropagate is being called");
		config.getBackpropagationStrategy().propagate(this, expectedValue);
	}
	
	public double simulate() {
		System.out.println("simulate is being called");
		return config.getSimulationStrategy().simulate(this);
	}
	
	public abstract void generateChildren();
	
	
}





