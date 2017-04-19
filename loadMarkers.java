package waze;

import java.io.File;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class loadMarkers {
	public static void main(String argv[]) {
		ArrayList<Node> nodeList = GetNodeEdge.getNodes();
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("markers");
			doc.appendChild(rootElement);

			for (int i = 0; i < nodeList.size(); i++) {
				Element child = doc.createElement("marker");
				rootElement.appendChild(child);
				child.setAttribute("id", String.valueOf(nodeList.get(i).getNodeId()));
				child.setAttribute("lat", String.valueOf(nodeList.get(i).getLat()));
				child.setAttribute("lng", String.valueOf(nodeList.get(i).getLng()));
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("WebContent/sample.xml"));

			transformer.transform(source, result);

			System.out.println("File saved!");

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
	}
}
