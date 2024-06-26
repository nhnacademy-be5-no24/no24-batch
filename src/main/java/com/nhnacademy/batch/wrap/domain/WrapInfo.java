package com.nhnacademy.batch.wrap.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nhnacademy.batch.order_detail.domain.OrderDetail;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 포장(WrapInfo) 테이블.
 *
 * @author : 박병휘
 * @date : 2024-04-25
 *
 **/
@Entity
@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WrapInfo {
    @EmbeddedId
    private Pk pk;

    @MapsId(value = "wrapId")
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "wrap_id")
    private Wrap wrap;

    @MapsId(value = "orderDetailId")
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "order_detail_id")
    private OrderDetail orderDetail;

    @Column(name = "amount")
    private Long amount;

    @NoArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "wrap_id")
        private Long wrapId;
        @Column(name = "order_detail_id")
        private Long orderDetailId;

        public Pk(Long wrapId, Long orderDetailId) {
            this.wrapId = wrapId;
            this.orderDetailId = orderDetailId;
        }
    }
}
