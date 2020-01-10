package io.jatoms.jaxrs.test;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JSONRequired;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;

@Component(service = ComponentImpl.class)
@JaxrsResource
@JSONRequired
public class ComponentImpl {
    
    @Path("/v0.4/traces")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void receiveTraces(List<List<Trace>> traces) {
        System.out.println("recieveTraces was called, traces were " + traces);
    }
    
}
