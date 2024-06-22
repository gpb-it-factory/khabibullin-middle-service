package gpb.dus.middle.exception.model;

import java.util.UUID;

public record ErrorV2(String message, String type, String code, UUID traceId) {

}

