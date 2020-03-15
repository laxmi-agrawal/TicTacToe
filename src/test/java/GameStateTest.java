import org.junit.Assert;
import org.junit.Test;

public class GameStateTest {
    @Test
    public void testUpdate(){
        GameState gameState = new GameState();
        Assert.assertTrue(gameState.update('X',0));
        Assert.assertFalse(gameState.update('X',1));
        Assert.assertFalse(gameState.update('O',0));
        Assert.assertTrue(gameState.update('O',4));
    }
}
