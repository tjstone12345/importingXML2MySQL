package importingXML;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;

/**
 * Use javax.xml.Parsers.DocumentBuilderFactory class to obtain a DOM parser to parse XML file
 * The parsed XML data is stored in List<Record> 
 * 
 * @author Jian Tang
 *
 */

public class XML_Handler {

	public List<Record> XML_Parser() {

		List<Record> input = new ArrayList<Record>(); // to store the parsed XML
													  // data
		try {

			File fXmlFile = new File("/Users/tjstone/Downloads/Software Engineer recruitment questions/data.xml"); // read file from local
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile); 
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("r");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					Record record = new Record();
					record.setName(eElement.getElementsByTagName("c0").item(0).getTextContent());
					record.setLastIPAddress(eElement.getElementsByTagName("c2").item(0).getTextContent());
					
					record.setResolution(eElement.getElementsByTagName("horizontal").item(0).getTextContent() + " * "
							+ eElement.getElementsByTagName("vertical").item(0).getTextContent()); // resolution is represented by
					                                                                               // "horizontal*vertical"
					
					record.setSerialNumber(eElement.getElementsByTagName("serial_number").item(0).getTextContent());
					input.add(record);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input;
	}

}