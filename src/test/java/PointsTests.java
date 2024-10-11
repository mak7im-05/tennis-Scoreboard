import com.maxim.tennisscoreboard.models.Match;
import com.maxim.tennisscoreboard.models.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PointsTests {
    @Test //40:40 - гейм продолжается
    public void caseTest1() {
        Match match = new Match();
        Player p1 = new Player();
        Player p2 = new Player();
        p1.setName("1");
        p2.setName("2");
        match.setPlayer1(p1);
        match.setPlayer2(p2);

        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);

        match.getScore().winPlayerPoints(2, 1);
        match.getScore().winPlayerPoints(2, 1);
        match.getScore().winPlayerPoints(2, 1);

        match.getScore().winPlayerPoints(1, 2);

        int result = match.getScore().getPlayerGames(1);

        Assertions.assertEquals(0, result);
    }

    @Test //45:0 = 1:0 game
    public void caseTest2() {
        Match match = new Match();
        Player p1 = new Player();
        Player p2 = new Player();
        p1.setName("1");
        p2.setName("2");
        match.setPlayer1(p1);
        match.setPlayer2(p2);

        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);

        int result = match.getScore().getPlayerGames(1);

        Assertions.assertEquals(1, result);
    }

    @Test // tye break
    public void caseTest3() {
        Match match = new Match();
        Player p1 = new Player();
        Player p2 = new Player();
        p1.setName("1");
        p2.setName("2");
        match.setPlayer1(p1);
        match.setPlayer2(p2);

        for (int i = 0; i < 5; ++i) {
            match.getScore().winPlayerPoints(1, 2);
            match.getScore().winPlayerPoints(1, 2);
            match.getScore().winPlayerPoints(1, 2);
            match.getScore().winPlayerPoints(1, 2);
        }

        for (int i = 0; i < 6; ++i) {
            match.getScore().winPlayerPoints(2, 1);
            match.getScore().winPlayerPoints(2, 1);
            match.getScore().winPlayerPoints(2, 1);
            match.getScore().winPlayerPoints(2, 1);
        }

        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);

        match.getScore().winPlayerPoints(1, 2);
        int result = match.getScore().getPlayerPoints(1);

        Assertions.assertEquals(1, result);
    }

    @Test // 1:0 - после 40:40 -> две победы подрят
    public void caseTest4() {
        Match match = new Match();
        Player p1 = new Player();
        Player p2 = new Player();
        p1.setName("1");
        p2.setName("2");
        match.setPlayer1(p1);
        match.setPlayer2(p2);

        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);

        match.getScore().winPlayerPoints(2, 1);
        match.getScore().winPlayerPoints(2, 1);
        match.getScore().winPlayerPoints(2, 1);

        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(2, 1);

        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);

        int result = match.getScore().getPlayerGames(1);
        Assertions.assertEquals(1, result);
    }

    @Test // isEnd game
    public void caseTest5() {
        Match match = new Match();
        Player p1 = new Player();
        Player p2 = new Player();
        p1.setName("1");
        p2.setName("2");
        match.setPlayer1(p1);
        match.setPlayer2(p2);

        for (int i = 0; i < 12; ++i) {
            match.getScore().winPlayerPoints(1, 2);
            match.getScore().winPlayerPoints(1, 2);
            match.getScore().winPlayerPoints(1, 2);
            match.getScore().winPlayerPoints(1, 2);
        }

        boolean result = match.getScore().isEnd();
        Assertions.assertTrue(result);
    }

    @Test // tye break in second sets
    public void caseTest6() {
        Match match = new Match();
        Player p1 = new Player();
        Player p2 = new Player();
        p1.setName("1");
        p2.setName("2");
        match.setPlayer1(p1);
        match.setPlayer2(p2);

        for (int i = 0; i < 6; ++i) {
            match.getScore().winPlayerPoints(1, 2);
            match.getScore().winPlayerPoints(1, 2);
            match.getScore().winPlayerPoints(1, 2);
            match.getScore().winPlayerPoints(1, 2);
        }

        for (int i = 0; i < 5; ++i) {
            match.getScore().winPlayerPoints(1, 2);
            match.getScore().winPlayerPoints(1, 2);
            match.getScore().winPlayerPoints(1, 2);
            match.getScore().winPlayerPoints(1, 2);
        }

        for (int i = 0; i < 6; ++i) {
            match.getScore().winPlayerPoints(2, 1);
            match.getScore().winPlayerPoints(2, 1);
            match.getScore().winPlayerPoints(2, 1);
            match.getScore().winPlayerPoints(2, 1);
        }

        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);
        match.getScore().winPlayerPoints(1, 2);

        match.getScore().winPlayerPoints(1, 2);

        int result = match.getScore().getPlayerPoints(1);
        Assertions.assertEquals(1, result);
    }
}
