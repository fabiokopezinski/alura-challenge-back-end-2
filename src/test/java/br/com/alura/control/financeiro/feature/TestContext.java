package br.com.alura.control.financeiro.feature;

import static java.lang.ThreadLocal.withInitial;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public enum TestContext {

    CONTEXT;

    private static final String RESPONSE = "RESPONSE";
    private static final String PAYLOAD = "PAYLOAD";
    private static final String IDS_TO_DELETE = "IDS_TO_DELETE";
    
    private final ThreadLocal<Map<String, Object>> testContexts = withInitial(HashMap::new);

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) testContexts.get().get(name);
    }

    public <T> T set(String name, T object) {
        testContexts.get().put(name, object);
        return object;
    }
     
    public <T> ResponseEntity<T> getResponse() {
        return get(RESPONSE);
    }
     
    public <T> void setResponse(ResponseEntity<T> response) {
        set(RESPONSE, response);
    }
    
    public <T> T getPayload(Class<T> clazz) {
        return clazz.cast(get(PAYLOAD));
    }

    public <T> void setPayload(T object) {
        set(PAYLOAD, object);
    }

    public <T> T getIdsToDelete() {
        return get(IDS_TO_DELETE);
    }
    
    public <T> void setIdsToDelete(T object) {
        set(IDS_TO_DELETE, object);
    }

    public void reset() {
        testContexts.get().clear();
    }
    
}
