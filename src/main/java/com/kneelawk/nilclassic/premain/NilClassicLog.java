package com.kneelawk.nilclassic.premain;

import nilloader.api.NilLogger;

public class NilClassicLog {
    // NilLoader comes with a logger abstraction that Does The Right Thing depending on the environment.
    // You should always use it.
    public static final NilLogger log = NilLogger.get("NilClassic");
}
