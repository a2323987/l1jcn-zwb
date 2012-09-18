/**
 *                            License
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS  
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
 * THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.  
 * ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR  
 * COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND  
 * AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE  
 * MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server.model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.lineage.server.datatables.BossSpawnTable;
import com.lineage.server.utils.PerformanceTimer;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.collections.Maps;

@XmlAccessorType(XmlAccessType.FIELD)
public class L1BossCycle {

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Base {
        @XmlAttribute(name = "Date")
        private String _date;

        @XmlAttribute(name = "Time")
        private String _time;

        public Base() {
            // TODO Auto-generated constructor stub
        }

        public String getDate() {
            return this._date;
        }

        public String getTime() {
            return this._time;
        }

        public void setDate(final String date) {
            this._date = date;
        }

        public void setTime(final String time) {
            this._time = time;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Cycle {
        @XmlAttribute(name = "Period")
        private String _period;

        @XmlAttribute(name = "Start")
        private String _start;

        @XmlAttribute(name = "End")
        private String _end;

        public String getEnd() {
            return this._end;
        }

        public String getPeriod() {
            return this._period;
        }

        public String getStart() {
            return this._start;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "BossCycleList")
    static class L1BossCycleList {
        @XmlElement(name = "BossCycle")
        private List<L1BossCycle> bossCycles;

        public List<L1BossCycle> getBossCycles() {
            return this.bossCycles;
        }

        public void setBossCycles(final List<L1BossCycle> bossCycles) {
            this.bossCycles = bossCycles;
        }
    }

    private static Logger _log = Logger.getLogger(L1BossCycle.class.getName());

    private static int getTimeParse(final String target, final String search) {
        if (target == null) {
            return 0;
        }
        int n = 0;
        final Matcher matcher = Pattern.compile("\\d+" + search)
                .matcher(target);
        if (matcher.find()) {
            final String match = matcher.group();
            n = Integer.parseInt(match.replace(search, ""));
        }
        return n;
    }

    @XmlAttribute(name = "Name")
    private String _name;

    @XmlElement(name = "Base")
    private Base _base;

    @XmlElement(name = "Cycle")
    private Cycle _cycle;

    private Calendar _baseDate;

    private int _period; // 分換算

    private int _periodDay;

    private int _periodHour;

    private int _periodMinute;

    private int _startTime; // 分換算

    private int _endTime; // 分換算

    private static SimpleDateFormat _sdfYmd = new SimpleDateFormat("yyyy/MM/dd");

    private static SimpleDateFormat _sdfTime = new SimpleDateFormat("HH:mm");

    private static SimpleDateFormat _sdf = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm");

    private static Date _initDate = new Date();

    private static String _initTime = "0:00";

    private static final Calendar START_UP = Calendar.getInstance();

    private static Map<String, L1BossCycle> _cycleMap = Maps.newMap();

    public static L1BossCycle getBossCycle(final String type) {
        return _cycleMap.get(type);
    }

    public static void load() {
        final PerformanceTimer timer = new PerformanceTimer();
        System.out.print("╠》正在读取 BossCycle...");
        try {
            // BookOrder クラスをバインディングするコンテキストを生成
            final JAXBContext context = JAXBContext
                    .newInstance(L1BossCycle.L1BossCycleList.class);

            // XML -> POJO 変換を行うアンマーシャラを生成
            final Unmarshaller um = context.createUnmarshaller();

            // XML -> POJO 変換
            final File file = new File("./data/xml/Cycle/BossCycle.xml");
            L1BossCycleList bossList = (L1BossCycleList) um.unmarshal(file);

            for (final L1BossCycle cycle : bossList.getBossCycles()) {
                cycle.init();
                _cycleMap.put(cycle.getName(), cycle);
            }

            // user覆盖任何数据
            final File userFile = new File(
                    "./data/xml/Cycle/users/BossCycle.xml");
            if (userFile.exists()) {
                bossList = (L1BossCycleList) um.unmarshal(userFile);

                for (final L1BossCycle cycle : bossList.getBossCycles()) {
                    cycle.init();
                    _cycleMap.put(cycle.getName(), cycle);
                }
            }
            // spawnlist_boss读取配置
            BossSpawnTable.fillSpawnTable();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, "BossCycle读取出现错误", e);
            System.exit(0);
        }
        System.out.println("完成!\t\t耗时: " + timer.get() + "\t毫秒");
    }

    /**
     * 检查指定的日期和时间含周期、计算出现的开始时间。
     * 
     * @return 周期出现的开始时间
     */
    public Calendar calcSpawnTime(final Calendar now) {
        // 获取参考日期
        final Calendar base = this.getBaseCycleOnTarget(now);
        // 计算出现的时期
        base.add(Calendar.MINUTE, this._startTime);
        // 出現時間の決定 start～end迄の間でランダムの秒
        final int diff = (this._endTime - this._startTime) * 60;
        final int random = diff > 0 ? Random.nextInt(diff) : 0;
        base.add(Calendar.SECOND, random);
        return base;
    }

    public Base getBase() {
        return this._base;
    }

    /*
     * 指定日時を含む周期(の開始時間)を返す ex.周期が2時間の場合 target base 戻り値 4:59 7:00 3:00 5:00 7:00
     * 5:00 5:01 7:00 5:00 6:00 7:00 5:00 6:59 7:00 5:00 7:00 7:00 7:00 7:01
     * 7:00 7:00 9:00 7:00 9:00 9:01 7:00 9:00
     */
    private Calendar getBaseCycleOnTarget(final Calendar target) {
        // 获取参考日期
        final Calendar base = (Calendar) this._baseDate.clone();
        if (target.after(base)) {
            // target <= baseとなるまで繰り返す
            while (target.after(base)) {
                base.add(Calendar.DAY_OF_MONTH, this._periodDay);
                base.add(Calendar.HOUR_OF_DAY, this._periodHour);
                base.add(Calendar.MINUTE, this._periodMinute);
            }
        }
        if (target.before(base)) {
            while (target.before(base)) {
                base.add(Calendar.DAY_OF_MONTH, -this._periodDay);
                base.add(Calendar.HOUR_OF_DAY, -this._periodHour);
                base.add(Calendar.MINUTE, -this._periodMinute);
            }
        }
        // 尝试计算完成时间、過去の時刻ならボス時間が過ぎている→次の周期を返す。
        final Calendar end = (Calendar) base.clone();
        end.add(Calendar.MINUTE, this._endTime);
        if (end.before(target)) {
            base.add(Calendar.DAY_OF_MONTH, this._periodDay);
            base.add(Calendar.HOUR_OF_DAY, this._periodHour);
            base.add(Calendar.MINUTE, this._periodMinute);
        }
        return base;
    }

    public Cycle getCycle() {
        return this._cycle;
    }

    /**
     * 对于一个指定的日期和时间、要返回最近出现的开始时间。
     * 
     * @return 近期出现的开始时间
     */
    public Calendar getLatestStartTime(final Calendar now) {
        // 获取参考日期
        final Calendar latestStart = this.getSpawnStartTime(now);
        if (!now.before(latestStart)) { // now >= latestStart
        } else {
            // now < latestStartなら1個前が最近の周期
            latestStart.add(Calendar.DAY_OF_MONTH, -this._periodDay);
            latestStart.add(Calendar.HOUR_OF_DAY, -this._periodHour);
            latestStart.add(Calendar.MINUTE, -this._periodMinute);
        }

        return latestStart;
    }

    public String getName() {
        return this._name;
    }

    /**
     * 取得一个指定的日期和时间含周期、计算出现的结束时间。
     * 
     * @return 周期出现的结束时间
     */
    public Calendar getSpawnEndTime(final Calendar now) {
        // 获取参考日期
        final Calendar endDate = this.getBaseCycleOnTarget(now);
        // 计算出现的时期
        endDate.add(Calendar.MINUTE, this._endTime);
        return endDate;
    }

    /**
     * 取得一个指定的日期和时间含周期、计算出现的开始时间。
     * 
     * @return 周期出现的开始时间
     */
    public Calendar getSpawnStartTime(final Calendar now) {
        // 获取参考日期
        final Calendar startDate = this.getBaseCycleOnTarget(now);
        // 计算出现的时期
        startDate.add(Calendar.MINUTE, this._startTime);
        return startDate;
    }

    public void init() throws Exception {
        // 参考日期和时间设置
        Base base = this.getBase();
        // 如果没有参考标准、以当前的日期0:00标准
        if (base == null) {
            this.setBase(new Base());
            this.getBase().setDate(_sdfYmd.format(_initDate));
            this.getBase().setTime(_initTime);
            base = this.getBase();
        } else {
            try {
                _sdfYmd.parse(base.getDate());
            } catch (final Exception e) {
                base.setDate(_sdfYmd.format(_initDate));
            }
            try {
                _sdfTime.parse(base.getTime());
            } catch (final Exception e) {
                base.setTime(_initTime);
            }
        }
        // 确定时间参考
        final Calendar baseCal = Calendar.getInstance();
        baseCal.setTime(_sdf.parse(base.getDate() + " " + base.getTime()));

        // 出现周期初始化,检查
        final Cycle spawn = this.getCycle();
        if ((spawn == null) || (spawn.getPeriod() == null)) {
            throw new Exception("CycleのPeriodは必須");
        }

        final String period = spawn.getPeriod();
        this._periodDay = getTimeParse(period, "d");
        this._periodHour = getTimeParse(period, "h");
        this._periodMinute = getTimeParse(period, "m");

        final String start = spawn.getStart();
        final int sDay = getTimeParse(start, "d");
        final int sHour = getTimeParse(start, "h");
        final int sMinute = getTimeParse(start, "m");
        final String end = spawn.getEnd();
        final int eDay = getTimeParse(end, "d");
        final int eHour = getTimeParse(end, "h");
        final int eMinute = getTimeParse(end, "m");

        // 分換算
        this._period = (this._periodDay * 24 * 60) + (this._periodHour * 60)
                + this._periodMinute;
        this._startTime = (sDay * 24 * 60) + (sHour * 60) + sMinute;
        this._endTime = (eDay * 24 * 60) + (eHour * 60) + eMinute;
        if (this._period <= 0) {
            throw new Exception("must be Period > 0");
        }
        // start補正
        if ((this._startTime < 0) || (this._period < this._startTime)) { // 補正
            this._startTime = 0;
        }
        // end補正
        if ((this._endTime < 0) || (this._period < this._endTime)
                || (end == null)) { // 補正
            this._endTime = this._period;
        }
        if (this._startTime > this._endTime) {
            this._startTime = this._endTime;
        }
        // start,endの相関補正(最低1分钟的间隔开启)
        // start==end还指定、出現時間が次の周期に被らないようにするため
        if (this._startTime == this._endTime) {
            if (this._endTime == this._period) {
                this._startTime--;
            } else {
                this._endTime++;
            }
        }

        // 最近の周期まで補正(再計算するときに厳密に算出するので、ここでは近くまで適当に補正するだけ)
        while (!(baseCal.after(START_UP))) {
            baseCal.add(Calendar.DAY_OF_MONTH, this._periodDay);
            baseCal.add(Calendar.HOUR_OF_DAY, this._periodHour);
            baseCal.add(Calendar.MINUTE, this._periodMinute);
        }
        this._baseDate = baseCal;
    }

    /**
     * 对于一个指定的日期和时间含周期、计算下一次出现的时间。
     * 
     * @return 未来周期的出现时间
     */
    public Calendar nextSpawnTime(final Calendar now) {
        // 获取参考日期
        final Calendar next = (Calendar) now.clone();
        next.add(Calendar.DAY_OF_MONTH, this._periodDay);
        next.add(Calendar.HOUR_OF_DAY, this._periodHour);
        next.add(Calendar.MINUTE, this._periodMinute);
        return this.calcSpawnTime(next);
    }

    public void setBase(final Base base) {
        this._base = base;
    }

    public void setCycle(final Cycle cycle) {
        this._cycle = cycle;
    }

    public void setName(final String name) {
        this._name = name;
    }

    /**
     * 周期名と指定日時に対する出現期間、产生出现时间
     * 
     * @param now
     *            周期产生的时间
     */
    public void showData(final Calendar now) {
        System.out.println("[Type]" + this.getName());
        System.out.print("  [出现期间]");
        System.out.print(_sdf.format(this.getSpawnStartTime(now).getTime())
                + " - ");
        System.out.println(_sdf.format(this.getSpawnEndTime(now).getTime()));
    }

}
