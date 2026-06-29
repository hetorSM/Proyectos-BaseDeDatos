package com.example.colegiohectorsm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class SistemaXML {
    //Escribe el archivo XML
    public static void escribirXML(File ruta, ArrayList<Alumno> listaAlumnos) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null, "xml", null);

            Element raiz = documento.createElement("Alumnos");
            documento.getDocumentElement().appendChild(raiz);

            Element nodoAlumno = null, nodoDatos = null;
            Text texto = null;

            for (Alumno persona : listaAlumnos) {
                nodoAlumno = documento.createElement("Alumno");
                raiz.appendChild(nodoAlumno);

                nodoDatos = documento.createElement("nombre");
                nodoAlumno.appendChild(nodoDatos);

                texto = documento.createTextNode(persona.getNombre());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("apellidos");
                nodoAlumno.appendChild(nodoDatos);

                texto = documento.createTextNode(persona.getApellidos());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("nia");
                nodoAlumno.appendChild(nodoDatos);

                texto = documento.createTextNode(persona.getNia());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("curso");
                nodoAlumno.appendChild(nodoDatos);

                texto = documento.createTextNode(persona.getCurso());
                nodoDatos.appendChild(texto);
            }

            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(ruta);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, resultado);
            System.out.println("El archivo alumno de XML fue creado exitosamente");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    //Lee un archivo XML
    public static ArrayList<Alumno> leerFicheroXML(File ruta) {
        ArrayList<Alumno> alumnoArrayList = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            documento = builder.parse(ruta);

            NodeList personas = documento.getElementsByTagName("Alumno");
            for (int i = 0; i < personas.getLength(); i++) {
                Node alumnoNodo = personas.item(i);
                Element elemento = (Element) alumnoNodo;

                alumnoArrayList.add(new Alumno(
                        elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue(),
                        elemento.getElementsByTagName("apellidos").item(0).getChildNodes().item(0).getNodeValue(),
                        elemento.getElementsByTagName("nia").item(0).getChildNodes().item(0).getNodeValue(),
                        elemento.getElementsByTagName("curso").item(0).getChildNodes().item(0).getNodeValue()
                ));
            }
            System.out.println("El archivo alumno de XML fue leido con exito");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return alumnoArrayList;
    }
}