package SAMExample;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class BookXMLHandler extends DefaultHandler {
    private Catalog catalog = null;
    private Book book = null;
    private StringBuilder currentCharacters = new StringBuilder();


    public Catalog getCatalog() {
        return catalog;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes
            attributes) {
        currentCharacters.setLength(0);

        if (qName.equalsIgnoreCase("catalog")) {
            catalog = new Catalog();
        } else if (qName.equalsIgnoreCase("book")) {
            book = new Book();
            String id = attributes.getValue("id");
            if (id != null) {
                book.setId(id);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String content = currentCharacters.toString().trim();

        if (qName.equalsIgnoreCase("book")) {
            catalog.push(book);
        } else if (qName.equalsIgnoreCase("author")) {
            book.setAuthor(content);
        } else if (qName.equalsIgnoreCase("title")) {
            book.setTitle(content);
        } else if (qName.equalsIgnoreCase("genre")) {
            book.setGenre(content);
        } else if (qName.equalsIgnoreCase("price")) {
            book.setPrice(Double.parseDouble(content));
        } else if (qName.equalsIgnoreCase("publish_date")) {
            book.setPublishDate(content);
        } else if (qName.equalsIgnoreCase("description")) {
            book.setDescription(content);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentCharacters.append(ch, start, length);
    }
}