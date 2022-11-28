import yeamy.sql.DsColumn;

public class Bean {

    public String ab_cd_ef;

    @DsColumn("ab_cd_ef")
    public String abcdef;

    public String abCdEf;

    public Extend extend;

    @Override
    public String toString() {
        return "full_math= " + ab_cd_ef
                + " | by_ann= " + abcdef
                + " | by_camel= " + abCdEf
                + " | extend= " + extend.abCdEf;
    }
    public static class Extend {
        public String abCdEf;
    }
}
