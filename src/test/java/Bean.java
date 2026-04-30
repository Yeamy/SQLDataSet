import yeamy.sql.ds.DsColumn;

import java.math.BigDecimal;
import java.sql.Date;

public class Bean {

    private int demo_id;

    private int demoId;

    @DsColumn("str")
    public String abcdef;

    public String str;

    public String createTime;

    public Extend extend;

    public Date date_time;

    public static class Extend {
        public BigDecimal bigdecimel;

        @Override
        public String toString() {
            return "Extend{ bigdecimel:" + bigdecimel + "}";
        }
    }

    @Override
    public String toString() {
        return "Bean{demo_id:" + demo_id + " demoId:" + demoId + " abcdef:" + abcdef + " str:" + str
                +" createTime:" + createTime + " extend:" + extend + " date_time:" + date_time + "}";
    }
}
