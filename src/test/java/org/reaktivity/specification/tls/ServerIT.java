/**
 * Copyright 2016-2017 The Reaktivity Project
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
package org.reaktivity.specification.tls;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.rules.RuleChain.outerRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;

public class ServerIT
{
    private final K3poRule k3po = new K3poRule()
        .addScriptRoot("scripts", "org/reaktivity/specification/tls");

    private final TestRule timeout = new DisableOnDebug(new Timeout(10, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(timeout);

    @Test
    @Specification({
        "${scripts}/connection.established/client",
        "${scripts}/connection.established/server"})
    //@ScriptProperty("clientAccept \"nukleus://target/streams/tls#source\"")
    public void shouldEstablishConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/echo.payload.length.10k/client",
        "${scripts}/echo.payload.length.10k/server"})
    //@ScriptProperty("clientAccept \"nukleus://target/streams/tls#source\"")
    public void shouldEchoPayloadLength10k() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/echo.payload.length.100k/client",
        "${scripts}/echo.payload.length.100k/server"})
    //@ScriptProperty("clientAccept \"nukleus://target/streams/tls#source\"")
    public void shouldEchoPayloadLength100k() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/echo.payload.length.1000k/client",
        "${scripts}/echo.payload.length.1000k/server"})
    //@ScriptProperty("clientAccept \"nukleus://target/streams/tls#source\"")
    public void shouldEchoPayloadLength1000k() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/server.sent.write.close/client",
        "${scripts}/server.sent.write.close/server"})
    public void shouldReceiveServerSentWriteClose() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/client.sent.write.close/client",
        "${scripts}/client.sent.write.close/server"})
    public void shouldReceiveClientSentWriteClose() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/server.sent.write.abort/client",
        "${scripts}/server.sent.write.abort/server"})
    public void shouldReceiveServerSentWriteAbort() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/client.sent.write.abort/client",
        "${scripts}/client.sent.write.abort/server"})
    public void shouldReceiveClientSentWriteAbort() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/server.sent.read.abort/client",
        "${scripts}/server.sent.read.abort/server"})
    public void shouldReceiveServerSentReadAbort() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/client.sent.read.abort/client",
        "${scripts}/client.sent.read.abort/server"})
    public void shouldReceiveClientSentReadAbort() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/client.hello.malformed/client",
        "${scripts}/client.hello.malformed/server"})
    public void shouldResetMalformedClientHello() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }
}
