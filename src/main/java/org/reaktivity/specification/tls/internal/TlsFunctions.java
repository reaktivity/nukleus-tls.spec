/**
 * Copyright 2016-2020 The Reaktivity Project
 *
 * The Reaktivity Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.reaktivity.specification.tls.internal;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.kaazing.k3po.lang.el.Function;
import org.kaazing.k3po.lang.el.spi.FunctionMapperSpi;
import org.reaktivity.specification.tls.internal.types.control.TlsRouteExFW;
import org.reaktivity.specification.tls.internal.types.stream.TlsBeginExFW;

public final class TlsFunctions
{
    @Function
    public static byte[] randomBytes(
        int length)
    {
        Random random = ThreadLocalRandom.current();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++)
        {
            bytes[i] = (byte) random.nextInt(0x100);
        }
        return bytes;
    }

    @Function
    public static TlsRouteExBuilder routeEx()
    {
        return new TlsRouteExBuilder();
    }

    @Function
    public static TlsBeginExBuilder beginEx()
    {
        return new TlsBeginExBuilder();
    }

    public static final class TlsRouteExBuilder
    {
        private final TlsRouteExFW.Builder routeExRW;

        private TlsRouteExBuilder()
        {
            final MutableDirectBuffer writeBuffer = new UnsafeBuffer(new byte[1024]);
            this.routeExRW = new TlsRouteExFW.Builder()
                                             .wrap(writeBuffer, 0, writeBuffer.capacity());
        }

        public TlsRouteExBuilder store(
            String store)
        {
            routeExRW.store(store);
            return this;
        }

        public TlsRouteExBuilder hostname(
            String hostname)
        {
            routeExRW.hostname(hostname);
            return this;
        }

        public TlsRouteExBuilder protocol(
            String protocol)
        {
            routeExRW.protocol(protocol);
            return this;
        }

        public byte[] build()
        {
            final TlsRouteExFW routeEx = routeExRW.build();
            final byte[] array = new byte[routeEx.sizeof()];
            routeEx.buffer().getBytes(routeEx.offset(), array);
            return array;
        }
    }

    public static final class TlsBeginExBuilder
    {
        private final TlsBeginExFW.Builder beginExRW;

        private TlsBeginExBuilder()
        {
            final MutableDirectBuffer writeBuffer = new UnsafeBuffer(new byte[1024]);
            this.beginExRW = new TlsBeginExFW.Builder()
                                             .wrap(writeBuffer, 0, writeBuffer.capacity());
        }

        public TlsBeginExBuilder typeId(
            int typeId)
        {
            beginExRW.typeId(typeId);
            return this;
        }

        public TlsBeginExBuilder hostname(
            String hostname)
        {
            beginExRW.hostname(hostname);
            return this;
        }

        public TlsBeginExBuilder protocol(
            String protocol)
        {
            beginExRW.protocol(protocol);
            return this;
        }

        public byte[] build()
        {
            final TlsBeginExFW beginEx = beginExRW.build();
            final byte[] array = new byte[beginEx.sizeof()];
            beginEx.buffer().getBytes(beginEx.offset(), array);
            return array;
        }
    }

    public static class Mapper extends FunctionMapperSpi.Reflective
    {

        public Mapper()
        {
            super(TlsFunctions.class);
        }

        @Override
        public String getPrefixName()
        {
            return "tls";
        }

    }

    private TlsFunctions()
    {
        // utility
    }
}
