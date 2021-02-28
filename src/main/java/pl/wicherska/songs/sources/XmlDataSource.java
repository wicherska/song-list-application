package pl.wicherska.songs.sources;

import pl.wicherska.songs.interfaces.DataSource;
import pl.wicherska.songs.xml.SongXmlRepresentation;
import pl.wicherska.songs.xml.SongsXmlRepresentation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class XmlDataSource implements DataSource<SongXmlRepresentation, String> {

    @Override
    public List<SongXmlRepresentation> readFromFiles(List<String> paths) {
        List<SongXmlRepresentation> songXmlList = new LinkedList<>();
        for (String path : paths) {
            songXmlList.addAll(readFromFile(path));
        }
        return songXmlList;
    }

    private List<SongXmlRepresentation> readFromFile(String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SongsXmlRepresentation.class);
            Unmarshaller jaxbContextUnmarshaller = jaxbContext.createUnmarshaller();
            SongsXmlRepresentation songsXmlRepresentationXml = (SongsXmlRepresentation) jaxbContextUnmarshaller.unmarshal(new File(path));
            return songsXmlRepresentationXml.getSongsList();
        } catch (JAXBException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return new SongsXmlRepresentation().getSongsList();
        }
    }

    @Override
    public void writeToFile(List<SongXmlRepresentation> songs, String path) throws JAXBException {
        SongsXmlRepresentation songsXmlRepresentation = new SongsXmlRepresentation();
        songsXmlRepresentation.setSongList(songs);
        JAXBContext jaxbContext = JAXBContext.newInstance(SongsXmlRepresentation.class);
        Marshaller jaxbContextMarshaller = jaxbContext.createMarshaller();
        jaxbContextMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbContextMarshaller.marshal(songsXmlRepresentation, new File(path));
    }
}
