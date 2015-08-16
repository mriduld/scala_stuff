import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertArrayEquals;

public class TestRandom {
    @Test
    public void testHashing() throws NoSuchAlgorithmException {
        int longLength = Long.SIZE / 8;
        MessageDigest e = MessageDigest.getInstance("MD5");
        String key = "TheRealMT";
        e.update(Bytes.toBytes(key));
        byte[] userHash = e.digest();
        byte[] timestamp = Bytes.toBytes(-1 * 1329088818321L);
        byte[] rowKey1 = Bytes.add(userHash, timestamp);

        byte[] rowKey = new byte[e.getDigestLength() + longLength];
        int offset = 0;
        offset = Bytes.putBytes(rowKey, offset, userHash, 0, userHash.length);
        Bytes.putBytes(rowKey, offset, timestamp, 0, timestamp.length);

        assertArrayEquals(rowKey, rowKey1);
    }
}
