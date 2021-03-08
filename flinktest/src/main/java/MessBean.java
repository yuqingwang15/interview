import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessBean {


    private String fromUid;
    private String toUid;
    private Long firtime;
    private Long num;
    private Long lastime;
    private String eventType;  // 事件类型: EventTypeEnum

    private Integer count = 0;  // 处理的次数

    public MessBean(String fromUid, String toUid, Long lastime, Long num) {
        this.fromUid = fromUid;
        this.toUid = toUid;
        this.lastime = lastime;
        this.num = num;
    }

    public MessBean(String fromUid, String toUid, Long lastime) {
        this.fromUid = fromUid;
        this.toUid = toUid;
        this.lastime = lastime;
    }

    public MessBean updateMess(String fromUid, String toUid, Long lastime, Long num) {
        return new MessBean(fromUid, toUid, lastime, num);
    }

    public MessBean addMess(String fromUid, String toUid, Long time) {
        return updateMess(fromUid, toUid, time, 1L);
    }

    public String getFromUid() {
        return fromUid;
    }

    public String getToUid() {
        return toUid;
    }
}