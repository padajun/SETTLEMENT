package com.example.settlement.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class MemberInfo {
    @Column(name = "member_id", nullable = false)
    private String memberId; // 회원 ID

    @Column(name = "member_name", nullable = false)
    private String memberName; // 회원이름20

    @Column(name = "member_phone_num", nullable = false)
    private String memberPhoneNum; // 회원 휴대전화번호

    @Column(name = "reservation_status", nullable = false)
    private String reservationStatus; // 예약상태

    @Builder
    public MemberInfo(String memberId, String memberName, String memberPhoneNum, String reservationStatus){
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPhoneNum =memberPhoneNum;
        this.reservationStatus = reservationStatus;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
        result = prime * result + ((memberName == null) ? 0 : memberName.hashCode());
        result = prime * result + ((memberPhoneNum == null) ? 0 : memberPhoneNum.hashCode());
        result = prime * result + ((reservationStatus == null) ? 0 : reservationStatus.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof MemberInfo))
            return false;
        MemberInfo other = (MemberInfo) obj;
        if (memberId == null) {
            if (other.memberId != null)
                return false;
        } else if (!memberId.equals(other.memberId))
            return false;
        if (memberName == null) {
            if (other.memberName != null)
                return false;
        } else if (!memberName.equals(other.memberName))
            return false;
        if (memberPhoneNum == null) {
            if (other.memberPhoneNum != null)
                return false;
        } else if (!memberPhoneNum.equals(other.memberPhoneNum))
            return false;
        if (reservationStatus == null) {
            if (other.reservationStatus != null)
                return false;
        } else if (!reservationStatus.equals(other.reservationStatus))
            return false;
        return true;
    }

    
}
