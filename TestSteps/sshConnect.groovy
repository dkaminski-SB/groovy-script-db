// Script that can open ssh session to other machine
import java.io.InputStream;
import java.util.Properties;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

try {

Properties config = new Properties();
config.put("PreferredAuthentications","gssapi-with-mic,publickey,keyboard-interactive,password");
config.put("StrictHostKeyChecking", "no");
config.put("HashKnownHosts", "yes");

JSch jsch=new JSch();
jsch.removeAllIdentity();
jsch.addIdentity("PRIVATE KEY FILE");

Session session=jsch.getSession("USER", "PW");
session.setConfig(config);

session.connect();

// Add here triggering of script that you want to execute via ssh

session.disconnect();

} catch (Exception e) {
    log.info ("ERROR")
    log.info (e)
}