package com.github.atomicblom.inspiration.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import static com.github.atomicblom.inspiration.util.Reference.MOD_ID;

public enum Logger
{
    INSTANCE;

    @SuppressWarnings({"NonSerializableFieldInSerializableClass", "InstanceVariableMayNotBeInitialized"})
    private org.apache.logging.log4j.Logger logger;

    public static void info(final String format, final Object... args)
    {
        INSTANCE.log(Level.INFO, format, args);
    }

    public static void log(final Level level, final Throwable exception, final String format, final Object... args)
    {
        //noinspection ChainedMethodCall
        INSTANCE.getLogger().log(level, String.format(format, args), exception);
    }

    public static void severe(final String format, final Object... args)
    {
        INSTANCE.log(Level.ERROR, format, args);
    }

    public static void warning(final String format, final Object... args)
    {
        INSTANCE.log(Level.WARN, format, args);
    }

    private org.apache.logging.log4j.Logger getLogger()
    {
        if (logger == null)
        {
            init();
        }

        return logger;
    }

    private void init()
    {
        if (logger == null)
        {
            logger = LogManager.getLogger(MOD_ID);
        }
    }

    private void log(final Level level, final String format, final Object... data)
    {
        //noinspection ChainedMethodCall
        getLogger().log(level, String.format(format, data));
    }
}