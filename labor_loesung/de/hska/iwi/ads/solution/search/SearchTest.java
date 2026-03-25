package de.hska.iwi.ads.solution.search;

import de.hska.iwi.ads.search.Search;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Provides a suite of test for Search implementations.
 * The test class for your implementation should use this
 * class as a base class. Add additional tests to your
 * test class.
 */
public abstract class SearchTest {

  /**
   Creates and returns an implementation of the Search interface.
   Override this method in the super class
   with, for instance, a BinarySearch implementation
   <pre>
   @override
   public &lt;E> extends Comparable&lt;E>> Search&lt;E> createSearch() {
     return new BinarySearch&lt;E>();
   }
   </pre>
   */
  public abstract <E extends Comparable<E>> de.hska.iwi.ads.search.Search<E> createSearch();
  
  @Test
  void testSearchIntegerArrayInteger() {
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    Integer [] a = {0, 2, 4, 7, 9, 10, 11};
    
    assertEquals(3, search.search(a, 7));
  }

  @Test
  void testSearchIntegerEArrayInteger1() {
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    Integer [] a = {0, 2, 4, 7, 9, 10, 11};
    
    assertEquals(3, search.search(a, 6));
  }
  
  @Test
  void testSearchIntegerArrayInteger2() {
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    Integer [] a = {0, 2, 4, 7, 9, 10, 11};
    
    assertEquals(6, search.search(a, 11));
  }
  
  @Test
  void testSearchIntegerArrayInteger3() {
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    Integer [] a = {0, 2, 4, 7, 9, 10, 11};
    
    assertEquals(0, search.search(a, 0));
  }
  
  @Test
  void testSearchIntegerArrayInteger4() {
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    Integer [] a = {0, 2, 4, 7, 9, 10, 11};
    
    assertEquals(7, search.search(a, 13));
  }
  
  @Test
  void testSearchIntegerArrayInteger5() {
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    Integer [] a = {1, 3, 5, 7, 9};
    
    assertEquals(3, search.search(a, 6));
  }
  
  @Test
  void testSearchIntegerArrayE5() {
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    Integer [] a = {0, 2, 4, 7, 9, 10, 11};
    
    assertEquals(-1, search.search(a, -2));
  }
  
  
  @Test
  void testSearchIntegerArrayIntegerIntInt() {
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    Integer [] a = {0, 2, 4, 7, 9, 10, 11};
    
    assertEquals(3, search.search(a, 7, 1, a.length - 2));
  }
  
  @Test
  void testSearchIntegerArrayIntegerIntInt1() {
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    Integer [] a = {0, 2, 4, 7, 9, 10, 11};
    
    assertEquals(3, search.search(a, 7, 3, 3));
  }
  
  @Test
  void testSearchIntegerArrayIntegerIntInt2() {
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    Integer [] a = {0, 2, 4, 7, 9, 10, 11};
    
    assertEquals(2, search.search(a, 5, 3, 3));
  }
  
  @Test
  void testSearchIntegerArrayIntegerIntInt3() {
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    Integer [] a = {0, 2, 4, 7, 9, 10, 11};
    
    assertEquals(2, search.search(a, 5, 3, 3));
  }
  
  @Test
  void testSearchIntegerArrayIntegerIntInt4() {
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    Integer [] a = {0, 2, 4, 7, 9, 10, 11};
    
    assertEquals(4, search.search(a, 9, 3, 3));
  }
  
  @Test
  void testSearchStringArrayString() {
    de.hska.iwi.ads.search.Search<String> search = createSearch();
    String [] a = {"a", "c", "e", "g"};
    assertEquals(2, search.search(a, "e", 0, a.length -1));
  }
  
  @Test
  void testSearchStringArrayString2() {
    de.hska.iwi.ads.search.Search<String> search = createSearch();
    String [] a = {"a", "black", "car", "moving", "over", "public", "roads", "to", "utah"};
    assertEquals(-1, search.search(a, " "));
  }
  
  @Test
  void testSearchStringArrayString3() {
    de.hska.iwi.ads.search.Search<String> search = createSearch();
    String [] a = {"a", "black", "car", "moving", "over", "public", "roads", "to", "utah"};
    assertEquals(0, search.search(a, "a"));
  }
  
  @Test
  void testSearchStringArrayString4() {
    de.hska.iwi.ads.search.Search<String> search = createSearch();
    String [] a = {"a", "black", "car", "moving", "over", "public", "roads", "to", "utah"};
    assertEquals(1, search.search(a, "b"));
  }
  
  @Test
  void testSearchStringArrayString5() {
    de.hska.iwi.ads.search.Search<String> search = createSearch();
    String [] a = {"a", "black", "car", "moving", "over", "public", "roads", "to", "utah"};
    assertEquals(4, search.search(a, "over"));
  }
  
  @Test
  void testSearchStringArrayString6() {
    de.hska.iwi.ads.search.Search<String> search = createSearch();
    String [] a = {"a", "black", "car", "moving", "over", "public", "roads", "to", "utah"};
    assertEquals(6, search.search(a, "rapidly"));
  }
  
  @Test
  void testSearchStringArrayString7() {
    de.hska.iwi.ads.search.Search<String> search = createSearch();
    String [] a = {"a", "black", "car", "moving", "over", "public", "roads", "to", "utah"};
    assertEquals(8, search.search(a, "utah"));
  }
  
  @Test
  void testSearchStringArrayString8() {
    de.hska.iwi.ads.search.Search<String> search = createSearch();
    String [] a = {"a", "black", "car", "moving", "over", "public", "roads", "to", "utah"};
    assertEquals(9, search.search(a, "washington"));
  }
  
  @Test
  void testSearchLocaleDateArrayLocaleDate() {
    LocalDate date = LocalDate.of(2018, Month.SEPTEMBER, 10);
    de.hska.iwi.ads.search.Search<ChronoLocalDate> search = createSearch();
    LocalDate [] dates = {date.minusMonths(2), date.minusDays(5), date, date.plusDays(5), date.plusMonths(2)};
    assertEquals(2, search.search(dates, date));
  }
  
  @Test
  void testSearchLocaleDateArrayLocaleDate1() {
    LocalDate date = LocalDate.of(2018, Month.SEPTEMBER, 10);
    de.hska.iwi.ads.search.Search<ChronoLocalDate> search = createSearch();
    LocalDate [] dates = {date.minusMonths(2), date.minusDays(5), date, date.plusDays(5), date.plusMonths(2)};
    assertEquals(3, search.search(dates, date.plusDays(1)));
  }

  @Test
  void testSearchIntArrayInt1() {
    Integer [] a = {1, 2, 2, 2, 3, 4, 5};
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    assertEquals(1, search.search(a, 2));
  }

  @Test
  void testSearchIntArrayInt2() {
    Integer [] a = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2};
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    assertEquals(0, search.search(a, 1));
  }

  @Test
  void testSearchIntArrayIntNull() {
    Integer [] a = null;
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    assertThrows(NullPointerException.class , () -> search.search(a, 1) );
  }

  @Test
  void testSearchIntArrayIntOutOfBounds() {
    Integer [] a = {0, 1, 2, 3, 4, 5};
    de.hska.iwi.ads.search.Search<Integer> search = createSearch();
    assertThrows(ArrayIndexOutOfBoundsException.class , () -> search.search(a, 2, 0, 7) );
  }
 
  @Test
  void testSearchIntArrayIntOutOfBounds1() {
    Integer [] a = {0, 1, 2, 3, 4, 5};
    Search<Integer> search = createSearch();
    assertThrows(ArrayIndexOutOfBoundsException.class , () -> search.search(a, 2, 0, -2) );
  }
}
