package a4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

/** @author Amy Huang, Maya Leong */
public class PhDTreeTest {
    private static final Professor prof1 = new Professor("Amy", "Huang");
    private static final Professor prof2 = new Professor("Maya", "Leong");
    private static final Professor prof3 = new Professor("Matthew", "Hui");
    private static final Professor prof4 = new Professor("Arianna", "Curillo");
    private static final Professor prof5 = new Professor("Michelle", "Gao");
    private static final Professor prof6 = new Professor("Richard", "Lyu");
    private static final Professor prof7 = new Professor("Eric", "Wells");
    private static final Professor prof8 = new Professor("Casey", "Lam");
    private static final Professor prof9 = new Professor("Julia", "Jacob");
    private static final Professor prof10 = new Professor("Matthew", "Martin");
    private static final Professor prof11 = new Professor("Daniel", "Rich");

    private static PhDTree tree1() {
        return new PhDTree(prof1, 2023);
    }
    private static PhDTree tree2() {
        return new PhDTree(prof4, 2019);
    }

    private static PhDTree tree3() {
        PhDTree t = new PhDTree(prof1, 1950);
        t.insert(prof1, prof2, 1960);
        t.insert(prof2, prof3, 1970);
        return t;
    }

    private static PhDTree tree4() {
        PhDTree t = new PhDTree(prof1, 1950);
        t.insert(prof1, prof2, 1960);
        t.insert(prof1, prof3, 1970);
        t.insert(prof1, prof4, 1973);
        t.insert(prof2, prof5, 1987);
        t.insert(prof2, prof6, 1984);
        t.insert(prof6, prof7, 1996);
        t.insert(prof7, prof8, 2005);
        t.insert(prof4, prof9, 2001);
        t.insert(prof4, prof10, 2003);
        return t;
    }

    @Test
    public void constructorTests() {
        assertEquals("Amy Huang", tree1().toString());
        assertEquals("Arianna Curillo", tree2().toString());
        assertEquals("Amy Huang[Maya Leong[Matthew Hui]]", tree3().toString());
        assertEquals("Amy Huang[Arianna Curillo[Julia Jacob, Matthew Martin], Matthew Hui, Maya Leong[Michelle Gao, Richard Lyu[Eric Wells[Casey Lam]]]]", tree4().toString());
    }

    @Test
    public void getterTests() {
        assertEquals(prof1, tree1().prof());
        // we have not inserted anything into the tree yet
        PhDTree t = new PhDTree(prof1, 2000);
        assertEquals(0, t.numAdvisees());
        assertEquals(0, tree1().numAdvisees());
        assertEquals(0, tree2().numAdvisees());
        assertEquals(1, tree3().numAdvisees());
        assertEquals(3, tree4().numAdvisees());
    }

    @Test
    public void insertTests() {
        // add professor 2 as a child of professor 1
        PhDTree t = new PhDTree(prof1, 1950);
        t.insert(prof1, prof2, 1960);
        assertEquals(1, t.numAdvisees());
        assertEquals(2, t.size());
        assertEquals("Amy Huang[Maya Leong]", t.toString());
        t.insert(prof2, prof3, 1970);
        assertEquals(1, t.numAdvisees());
        assertEquals(3, t.size());
        assertEquals("Amy Huang[Maya Leong[Matthew Hui]]", t.toString());
        assertEquals(3, tree4().numAdvisees());
        assertEquals(10, tree4().size());
    }

    @Test
    public void findTreeTests() {
        PhDTree tree1 = tree1();
        tree1.insert(prof1, prof2, 1950);
        tree1.insert(prof2, prof3, 1960);
        PhDTree tree4 = new PhDTree(prof2, 1950);
        tree4.insert(prof2, prof3, 1980);
        try {
            assertEquals(tree4.prof(), tree1.findTree(prof2).prof());
            assertEquals("Maya Leong[Matthew Hui]", tree1.findTree(prof2).toString());
        } catch (NotFound e) { fail("Not found"); }
        assertThrows(NotFound.class, () -> tree2().findTree(prof5));
        assertThrows(NotFound.class, () -> tree1().findTree(prof4));
        try {
            assertEquals(1, tree1.findTree(prof3).size());
        } catch (NotFound e) {
            fail("Not found");
        }
        tree1.insert(prof1, prof4, 1967);
        tree1.insert(prof1, prof7, 1964);
        try {
            assertEquals(tree4.prof(), tree1.findTree(prof2).prof());
            assertEquals("Maya Leong[Matthew Hui]", tree1.findTree(prof2).toString());
        } catch (NotFound e) { fail("Not found"); }
        assertThrows(NotFound.class, () -> tree2().findTree(prof5));
        assertThrows(NotFound.class, () -> tree1().findTree(prof6));
        try {
            assertEquals(1, tree1.findTree(prof3).size());
            assertEquals(2, tree1.findTree(prof2).size());
            assertEquals(5, tree1.findTree(prof1).size());
        } catch (NotFound e) {
            fail("Not found");
        }
    }

    @Test
    public void sizeTest() {
        PhDTree t = new PhDTree(prof1, 1900);
        t.insert(prof1, prof2, 1920);
        t.insert(prof2, prof3, 1930);
        assertEquals(3, t.size());
        assertEquals(10, tree4().size());
        try {
            assertEquals(5, tree4().findTree(prof2).size());
            assertEquals(10, tree4().findTree(prof1).size());
            assertEquals(3, tree4().findTree(prof4).size());
            assertEquals(1, tree4().findTree(prof3).size());
            assertEquals(2, tree4().findTree(prof7).size());
        } catch (NotFound e) {
            fail("Not found");
        }
    }

    @Test
    public void containsTest() {
        PhDTree t = new PhDTree(prof1, 1900);
        t.insert(prof1, prof2, 1920);
        t.insert(prof2, prof3, 1930);
        Professor ro = new Professor("Isaac", "Newton");
        PhDTree pt = new PhDTree(ro, 1668);
        assertTrue(t.contains(new Professor("Amy", "Huang")));
        assertTrue(t.contains(new Professor("Matthew", "Hui")));
        assertTrue(pt.contains(new Professor("Isaac", "Newton")));
        assertFalse(t.contains(prof6));
        assertTrue(tree4().contains(prof1));
        assertTrue(tree4().contains(prof2));
        assertTrue(tree4().contains(prof3));
        assertTrue(tree4().contains(prof4));
        assertTrue(tree4().contains(prof5));
        assertTrue(tree4().contains(prof6));
        assertTrue(tree4().contains(prof7));
        assertTrue(tree4().contains(prof8));
        assertTrue(tree4().contains(prof9));
        assertTrue(tree4().contains(prof10));
        assertFalse(tree4().contains(prof11));
    }

    @Test
    public void findAcademicLineageTest() {
        PhDTree t = new PhDTree(prof1, 1900);
        t.insert(prof1, prof2, 1920);
        t.insert(prof1, prof4, 1920);
        t.insert(prof2, prof3, 1930);
        t.insert(prof2, prof5, 1930);
        List<Professor> lineage1 = new LinkedList<>();
        lineage1.add(prof1);
        lineage1.add(prof2);
        lineage1.add(prof3);
        List<Professor> lineage2 = new LinkedList<>();
        lineage2.add(prof1);
        try {
            assertEquals(lineage2, t.findAcademicLineage(prof1));
            assertEquals(lineage1, t.findAcademicLineage(prof3));
        } catch (NotFound e) {
            fail("Not found");
        }
        List<Professor> lineage3 = new LinkedList<>();
        lineage3.add(prof1);
        lineage3.add(prof4);
        lineage3.add(prof9);
        List<Professor> lineage4 = new LinkedList<>();
        lineage4.add(prof1);
        lineage4.add(prof2);
        lineage4.add(prof6);
        lineage4.add(prof7);
        try {
            assertEquals(lineage3, tree4().findAcademicLineage(prof9));
            assertEquals(lineage4, tree4().findAcademicLineage(prof7));
        } catch (NotFound e) {
            fail("Not found");
        }
    }

    @Test
    public void commonAncestorTest() {
        PhDTree t = tree3();
        try {
            assertEquals(prof2, t.commonAncestor(prof2, prof3));
            assertEquals(prof1, t.commonAncestor(prof1, prof3));
            assertEquals(prof1, tree4().commonAncestor(prof3, prof6));
            assertEquals(prof2, tree4().commonAncestor(prof5, prof7));
            assertEquals(prof1, tree4().commonAncestor(prof8, prof9));
            assertEquals(prof1, tree4().commonAncestor(prof3, prof10));
            assertEquals(prof4, tree4().commonAncestor(prof9, prof10));
        } catch (NotFound exc) {
            fail("Not found");
        }
        assertThrows(NotFound.class, () -> t.commonAncestor(prof5, prof3));
    }

    @Test
    public void maxDepthTest() {
        PhDTree t = tree3();
        assertEquals(2, t.maxDepth());
        assertEquals(4, tree4().maxDepth());
        try {
            assertEquals(3, tree4().findTree(prof2).maxDepth());
            assertEquals(1, tree4().findTree(prof4).maxDepth());
        } catch (NotFound e) {
            fail("Not found");
        }
    }

    @Test
    public void getAdvisorTest() {
        PhDTree t = tree3();
        try {
            assertEquals(prof2.toString(), t.findAdvisor(prof3).toString());
            assertEquals(prof1.toString(), tree4().findAdvisor(prof2).toString());
            assertEquals(prof1.toString(), tree4().findAdvisor(prof3).toString());
            assertEquals(prof1.toString(), tree4().findAdvisor(prof4).toString());
            assertEquals(prof2.toString(), tree4().findAdvisor(prof5).toString());
            assertEquals(prof2.toString(), tree4().findAdvisor(prof6).toString());
            assertEquals(prof6.toString(), tree4().findAdvisor(prof7).toString());
            assertEquals(prof7.toString(), tree4().findAdvisor(prof8).toString());
            assertEquals(prof4.toString(), tree4().findAdvisor(prof9).toString());
            assertEquals(prof4.toString(), tree4().findAdvisor(prof10).toString());
        } catch (NotFound e) {
            fail("Not found");
        }
        assertThrows(NotFound.class, () -> t.findAdvisor(prof1));
    }

    @Test
    public void toString1() {
        PhDTree t = tree3();
        String[] lines = t.toString().split("\n");
        String[] expected = {"Amy Huang[Maya Leong[Matthew Hui]]"};
        Arrays.sort(lines);
        Arrays.sort(expected);
        assertTrue(Arrays.equals(lines, expected));
    }

    @Test
    public void toStringVerbose() {
        PhDTree t = tree3();
        String[] lines = t.toStringVerbose().split("\n");
        String[] expected = {
            "Amy Huang - 1950",
            "Maya Leong - 1960",
            "Matthew Hui - 1970"
        };
        Arrays.sort(lines);
        Arrays.sort(expected);
        assertTrue(Arrays.equals(lines, expected));
        String[] lines1 = tree4().toStringVerbose().split("\n");
        String[] expected1 = {
                "Amy Huang - 1950",
                "Arianna Curillo - 1973",
                "Julia Jacob - 2001",
                "Matthew Martin - 2003",
                "Matthew Hui - 1970",
                "Maya Leong - 1960",
                "Michelle Gao - 1987",
                "Richard Lyu - 1984",
                "Eric Wells - 1996",
                "Casey Lam - 2005"
        };
        Arrays.sort(lines1);
        Arrays.sort(expected1);
        assertTrue(Arrays.equals(lines1, expected1));
    }
}
