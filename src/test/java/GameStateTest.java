import org.junit.Assert;
import org.junit.Test;

public class GameStateTest {
    @Test
    public void testUpdateWinner(){
        GameState gameState = new GameState();
        Assert.assertEquals(GameState.NEXT_MOVE,gameState.update('X',0));
        Assert.assertEquals(GameState.INVALID,gameState.update('X',1));
        Assert.assertEquals(GameState.INVALID,gameState.update('O',0));
        Assert.assertEquals(GameState.NEXT_MOVE,gameState.update('O',4));
        Assert.assertEquals(GameState.NEXT_MOVE,gameState.update('X',1));
        Assert.assertEquals(GameState.NEXT_MOVE,gameState.update('O',6));
        Assert.assertEquals(GameState.WINNER,gameState.update('X',2));
    }
    @Test
    public void testUpdateTie(){
        GameState gameState = new GameState();
        Assert.assertEquals(GameState.NEXT_MOVE,gameState.update('X',0));
        Assert.assertEquals(GameState.NEXT_MOVE,gameState.update('O',4));
        Assert.assertEquals(GameState.NEXT_MOVE,gameState.update('X',1));
        Assert.assertEquals(GameState.NEXT_MOVE,gameState.update('O',2));
        Assert.assertEquals(GameState.NEXT_MOVE,gameState.update('X',6));
        Assert.assertEquals(GameState.NEXT_MOVE,gameState.update('O',3));
        Assert.assertEquals(GameState.NEXT_MOVE,gameState.update('X',5));
        Assert.assertEquals(GameState.NEXT_MOVE,gameState.update('O',8));
        Assert.assertEquals(GameState.TIE,gameState.update('X',7));
    }
}
