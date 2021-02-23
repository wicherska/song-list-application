package pl.wicherska.songs.xml;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class XmlDataSource{
    private JAXBContext jaxbContext;

//    ToDo synchro
    private JAXBContext getJaxbInstance() throws JAXBException {
        if (jaxbContext == null){
            jaxbContext = JAXBContext.newInstance(SongsXmlRepresentation.class);
        }
        return jaxbContext;
    }

    public List<SongXmlRepresentation> readFromFiles(List<String> paths){
        List<SongXmlRepresentation> songXmlList = new LinkedList<>();
        for(String path: paths){
            songXmlList.addAll(readFromFile(path));
        }
        return songXmlList;
    }



    private List<SongXmlRepresentation> readFromFile(String path){
        try{
            Unmarshaller jaxbContextUnmarshaller = this.getJaxbInstance().createUnmarshaller();
            SongsXmlRepresentation songsXmlRepresentationXml = (SongsXmlRepresentation) jaxbContextUnmarshaller.unmarshal(new File(path));
            return songsXmlRepresentationXml.getSongsList();
        } catch(JAXBException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            return new SongsXmlRepresentation().getSongsList();
        }
    }

    public void writeToFile(List<SongXmlRepresentation> songs, String path) throws JAXBException {
        SongsXmlRepresentation songsXmlRepresentation = new SongsXmlRepresentation();
        songsXmlRepresentation.setSongList(songs);

        Marshaller jaxbContextMarshaller = this.getJaxbInstance().createMarshaller();
        jaxbContextMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbContextMarshaller.marshal(songsXmlRepresentation, new File(path));
    }
}
