package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class DatedCode {

    private static final String pattern = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);

    @Id
    @Column/*(name = "id")*/
    @JsonIgnore
    private Integer id;

    @Column(name = "uuid")
    @JsonIgnore
    private UUID uuid;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private String date;

    @Column(name = "viewed")
    @JsonIgnore
    private String lastViewed;

    @Column(name = "views")
    private int views;

    @Column(name = "time")
    private int time;

    @Column(name = "viewsRestricted")
    @JsonIgnore
    private boolean viewsRestricted;

    @Column(name = "timeRestricted")
    @JsonIgnore
    private boolean timeRestricted;

    @Column
    @JsonIgnore
    private boolean expired;

    public DatedCode() {
    }

    public DatedCode(final String code, final LocalDateTime date) {
        this.code = code;
        this.date = formatDate(date);
    }

    public DatedCode(final int id, final UUID uuid, final String code, final LocalDateTime date) {
        this(code, date);
        this.id = id;
        this.uuid = uuid;
    }

    public DatedCode(final int id, final UUID uuid, final String code, final LocalDateTime date, final int views, final int time) {
        this(id, uuid, code, date);
        this.views = views;
        if (views != 0) {
            viewsRestricted = true;
        }
        this.time = time;
        if (time != 0) {
            timeRestricted = true;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public void setDateTime(final LocalDateTime localDateTime) {
        setDate(formatDate(localDateTime));
    }

    public int getViews() {
        return views;
    }

    public void setViews(final int views) {
        this.views = views;
        if (views != 0) {
            viewsRestricted = true;
        }
    }

    public int getTime() {
        return time;
    }

    public void setTime(final int time) {
        this.time = time;
        if (time != 0) {
            timeRestricted = true;
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isViewsRestricted() {
        return viewsRestricted;
    }

    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    @JsonIgnore
    public boolean isRestricted() {
        return timeRestricted || viewsRestricted;
    }

    public void addView() {
        if (!isRestricted() || views == 0) {
            return;
        }
        views--;
    }

    public void checkTime() {
        time = Math.max(time - getTimePassed(), 0);
        lastViewed = formatDate(LocalDateTime.now());
    }


    public void updateRestrictions() {
        if (!isRestricted()) {
            return;
        }
        addView();
        if (isViewsRestricted() && views == 0) {
            this.expired = true;
            return;
        }
        checkTime();
        if (isTimeRestricted() && time == 0) {
            this.expired = true;
        }
    }

    @JsonIgnore
    public int getTimePassed() {
        if (lastViewed == null) {
            lastViewed = date;
        }
        return (int) Duration.between(LocalDateTime.parse(lastViewed, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.now()).getSeconds();
    }

    @JsonIgnore
    public boolean isExpired() {
        return isRestricted() && expired;
    }

    @Override
    public String toString() {
        return "DatedCode{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", lastViewed='" + lastViewed + '\'' +
                ", views=" + views +
                ", time=" + time +
                ", viewsRestricted=" + viewsRestricted +
                ", timeRestricted=" + timeRestricted +
                ", expired=" + expired +
                '}';
    }

    private String formatDate(final LocalDateTime dateTime) {
        return dateTime.format(format);
    }
}
