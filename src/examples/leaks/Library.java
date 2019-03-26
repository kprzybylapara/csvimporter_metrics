/*
 * Created on Sep 25, 2003
 */
package examples.leaks;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class providing static access to the library.
 */
public class Library {
    
    /**
	 * Private constructor for utility class 'Library'
	 */
	private Library() {
	}
    
    private static final int _S_CHARS_PER_LINE = 80;
    
    private static final int _S_LINES_PER_PAGE = 50;

	private static Set _s_books = new HashSet ();
    
    /**
     * Add a new book to the library.
     * @param title The book title.
     * @param author The book author.
     * @pre title != null
     * @pre author != null
     * @pre num_pages > 500
     */
    public static void addBook (String title, String author, int num_pages) {
        int num_chars = num_pages * _S_LINES_PER_PAGE * _S_CHARS_PER_LINE; 
        _s_books.add (new Book (title, author, new char [num_chars]));
        // A memory leak will be reported for the above line because it
        // allocates memory that will never be freed each time this method is
        // called with the same arguments.
    }
    
    /**
     * Get all the books in the library.
     * @return Book []
     */
    public static Book [] getBooks () {
        return (Book []) _s_books.toArray (new Book [_s_books.size ()]);
    }


    /**
     * Object representation of a book.
     */
    public static class Book {
    
        private String _title;
    
        private String _author;
        
        private char [] _contents;
    
        Book (String title, String author, char [] contents) {
            _title = title;
            _author = author;
            _contents = contents;
        }
    
        /**
         * Get the title of this book.
         * @return String
         */
        public String getTitle () {
            return _title;
        }
    
        /**
         * Get the author of this book.
         * @return String
         */
        public String getAuthor () {
            return _author;
        }
        
        // Uncomment the new implementations of hashCode () and equals ()
        // to fix the memory leak.  With these methods, duplicate entries
        // of the same book will not be allowed in the library.  Invoking
        // addBook () with the same arguments many times will result in only
        // one Book object that cannot be garbage collected, rather than
        // a Book object for each addBook () invocation.
    
//        /**
//         * @see java.lang.Object#hashCode()
//         */
//        public int hashCode () {
//            return _title.hashCode() + _author.hashCode();
//        }
//    
//        /**
//         * @see java.lang.Object#equals(java.lang.Object)
//         */
//        public boolean equals (Object o) {
//            return (o instanceof Book) &&
//                _title.equals (((Book) o)._title) &&
//                _author.equals (((Book) o)._author);
//        }
    }
}