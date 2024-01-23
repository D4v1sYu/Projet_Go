package jeu_de_go;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashSet;

import org.junit.Test;

public class GoTest {

    @Test
    public void testCapture() {
        Go jeu1 = new Go();
        assert(jeu1.play(0, 0, "WHITE"));
        assertFalse(jeu1.play(0, 0, "BLACK"));
        assert(jeu1.play(1, 0, "BLACK"));
        assert(jeu1.checkCapture(0, 1, Color.BLACK));
        assert(jeu1.play(0, 1, "BLACK"));
        assertFalse(jeu1.play(0, 0, "WHITE"));

        Go jeu2 = new Go();
        assert(jeu2.play(1, 0, "BLACK"));
        assert(jeu2.play(0, 1, "BLACK"));
        assertFalse(jeu2.play(0, 0, "WHITE"));
        jeu2.addScore("BLACK", 1);

        assertEquals(jeu1.showboard(), jeu2.showboard());
    }
    @Test
    public void testCaptureLiberte(){

        Go jeu1 = new Go();
        assert(jeu1.play(0, 0, "WHITE"));
        assert(jeu1.play(1, 0, "WHITE"));

        assert(jeu1.play(2, 0, "BLACK"));
        assert(jeu1.play(1, 1, "BLACK"));

        assertEquals(2,jeu1.getLiberties(2, 0, new HashSet<>()));
        assertEquals(1,jeu1.getLiberties(1, 0, new HashSet<>()));
        assertEquals(1,jeu1.getLiberties(0, 0, new HashSet<>()));
        assertEquals(3,jeu1.getLiberties(1, 1, new HashSet<>()));

        assert(jeu1.checkCapture(0, 1, Color.BLACK));
        assert(jeu1.play(0, 1, "BLACK"));

        Go jeu2 = new Go();
        assert(jeu2.play(2, 0, "BLACK"));
        assert(jeu2.play(1, 1, "BLACK"));
        assert(jeu2.play(0, 1, "BLACK"));
        jeu2.addScore("BLACK", 2);

        assertEquals(jeu1.showboard(), jeu2.showboard());
    }

    @Test
    public void testLiberte(){
        Go jeu1 = new Go();
        assert(jeu1.play(0, 0, "BLACK"));
        assertEquals(2,jeu1.getLiberties(0, 0, new HashSet<>()));
        assert(jeu1.play(0, 1, "BLACK"));
        assertEquals(jeu1.getLiberties(0, 1, new HashSet<>()),jeu1.getLiberties(0, 0, new HashSet<>()));
        assert(jeu1.play(1, 0, "BLACK"));
        assertEquals(3,jeu1.getLiberties(0, 0, new HashSet<>()));

        assert(jeu1.play(1, 2, "BLACK"));
        assertEquals(4,jeu1.getLiberties(1, 2, new HashSet<>()));
        assert(jeu1.play(2, 1, "BLACK"));
        assertEquals(4,jeu1.getLiberties(2, 1, new HashSet<>()));
        assert(jeu1.play(2, 2, "BLACK"));
        assertEquals(7,jeu1.getLiberties(2, 2, new HashSet<>()));

        assert(jeu1.play(1, 1, "BLACK"));
        assertEquals(6,jeu1.getLiberties(0, 0, new HashSet<>()));

        assert(jeu1.play(2, 0, "WHITE"));
        assert(jeu1.play(3, 1, "WHITE"));
        assert(jeu1.play(3, 2, "WHITE"));
        assert(jeu1.play(2, 3, "WHITE"));
        assert(jeu1.play(1, 3, "WHITE"));
        
        assertEquals(1,jeu1.getLiberties(0, 0, new HashSet<>()));
    }

    @Test
    public void testko(){
        Go jeu1 = new Go();
        assert(jeu1.play(0, 1, "BLACK"));
        assert(jeu1.play(0, 0, "WHITE"));
        assert(jeu1.checkCapture(1, 0, Color.BLACK));
        assert(jeu1.play(1,0, "BLACK"));
        assert(jeu1.checkKo(0, 0, Color.WHITE) == jeu1.play(0, 0, "WHITE"));
    }

    @Test
    public void testskip(){
        Go jeu1 = new Go();
        assert(jeu1.getCurrent() == Color.BLACK);
        jeu1.skip();
        assert(jeu1.play(0, 0, "WHITE"));
        assert(jeu1.getCurrent() == Color.BLACK);
    }
}
