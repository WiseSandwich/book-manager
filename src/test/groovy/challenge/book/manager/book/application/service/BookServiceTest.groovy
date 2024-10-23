package challenge.book.manager.book.application.service

import challenge.book.manager.book.application.port.in.LanguageUseCase
import challenge.book.manager.book.application.port.out.BookDBPort
import challenge.book.manager.book.application.port.out.OpenLibraryPort
import challenge.book.manager.book.domain.Book
import challenge.book.manager.book.domain.Language
import challenge.book.manager.common.exception.ResourceNotFoundException
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class BookServiceTest extends Specification{

    private  BookDBPort bookDBPort = Mock();
    private  LanguageUseCase languageUseCase = Mock();
    private  OpenLibraryPort openLibraryPort = Mock();

    @Subject
    private BookService bookService= new BookService(bookDBPort, languageUseCase, openLibraryPort);


    @Unroll
    def "findBook should return a book when found"() {
        given:
        def bookId = UUID.randomUUID().toString()
        def expectedBook = new Book(id: UUID.fromString(bookId), title: "Test Book")

        and:
        bookDBPort.findByBookId(UUID.fromString(bookId)) >> expectedBook

        when:
        def result = bookService.findBook(bookId)

        then:
        result == expectedBook
    }

    @Unroll
    def "findAllBook should return all books"() {
        given:
        def books = [new Book(title: "Book 1"), new Book(title: "Book 2")]

        and:
        bookDBPort.findAllBooks() >> books

        when:
        def result = bookService.findAllBook()

        then:
        result == books
    }

    @Unroll
    def "saveBook should validate and save the book"() {
        given:
        def book = new Book(title: "Test Book", languages: [new Language(languageName: "English")])
        def languages = [new Language(languageName: "English")]

        and:
        languageUseCase.findLanguages(_ as Set<String>) >> languages
        bookDBPort.findAllBooksByTitle(_ as String) >> []

        when:
        bookService.saveBook(book)

        then:
        1 * bookDBPort.saveBook(book)
    }

    @Unroll
    def "findAllBooksByTitle should return books from DB when API is empty"() {
        given:
        def title = "Some Book"
        def bookList = [new Book(title: title)]

        and:
        bookDBPort.findAllBooksByTitle(title) >> bookList

        when:
        def result = bookService.findAllBooksByTitle(title)

        then:
        result == bookList
    }

    @Unroll
    def "validateLanguages should throw ResourceNotFoundException when no languages found"() {
        given:
        def book = new Book(languages: [new Language(languageName: "any language")])

        and:
        languageUseCase.findLanguages(_ as Set<String>) >> []

        when:
        bookService.saveBook(book)

        then:
        thrown(ResourceNotFoundException)
    }

}