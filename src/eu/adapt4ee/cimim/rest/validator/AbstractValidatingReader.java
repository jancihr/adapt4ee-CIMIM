package eu.adapt4ee.cimim.rest.validator;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public abstract class AbstractValidatingReader<T> implements
    MessageBodyReader<T> {

@Context
protected Providers providers;

@SuppressWarnings("unchecked")
@Override
public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2,
        MediaType arg3) {

    Class<T> readableClass = (Class<T>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];
    return arg0 == readableClass;
}

@SuppressWarnings("unchecked")
@Override
public T readFrom(Class<T> arg0, Type arg1, Annotation[] arg2,
        MediaType arg3, MultivaluedMap<String, String> arg4,
        InputStream arg5) throws IOException, WebApplicationException {

    T type = null;
    JAXBContext jaxbContext = null;
    ContextResolver<JAXBContext> resolver = providers.getContextResolver(
            JAXBContext.class, arg3);
    try {

        if (resolver != null) {
            jaxbContext = resolver.getContext(arg0);
        }

        if (jaxbContext == null) {
            jaxbContext = JAXBContext.newInstance(arg0);

        }
        type = (T) jaxbContext.createUnmarshaller().unmarshal(arg5);
        validate(type);

    } catch (JAXBException e) {
        throw new WebApplicationException(
                Response.Status.INTERNAL_SERVER_ERROR);
    }

    return type;
}

protected abstract void validate(T arg0) throws WebApplicationException;
}