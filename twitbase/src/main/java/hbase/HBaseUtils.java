package hbase;

import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.util.Bytes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HBaseUtils {
    public static byte[] md5(String input ) {
        MessageDigest e;
        try {
            e = MessageDigest.getInstance("MD5");
            e.update(Bytes.toBytes(input));
            return e.digest();
        } catch (NoSuchAlgorithmException e1) {
            throw new RuntimeException(e1);
        }
    }

    public static byte[] nextClosest(byte[] rowKeyPrefix){
        int offset = rowKeyPrefix.length;
        while (offset > 0) {
            if (rowKeyPrefix[offset - 1] != (byte) 0xFF) {
                break;
            }
            offset--;
        }
        if (offset == 0) {
            return HConstants.EMPTY_END_ROW;
        }
        byte[] newStopRow = Arrays.copyOfRange(rowKeyPrefix, 0, offset);
        newStopRow[newStopRow.length - 1]++;
        return newStopRow;
    }
}
