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
public class BusinessInfo {
    @Column(name = "business_id", nullable = false)
    private String businessId;

    @Column(name = "business_name", nullable = false)
    private String businessName;      // 사업장명
    
    @Column(name = "business_phone_num", nullable = false)
    private String businessPhoneNum;  // 사업장연락처

    @Builder
    public BusinessInfo(String businessId, String businessName, String businessPhoneNum){
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessPhoneNum = businessPhoneNum;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((businessId == null) ? 0 : businessId.hashCode());
        result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
        result = prime * result + ((businessPhoneNum == null) ? 0 : businessPhoneNum.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof BusinessInfo))
            return false;
        BusinessInfo other = (BusinessInfo) obj;
        if (businessId == null) {
            if (other.businessId != null)
                return false;
        } else if (!businessId.equals(other.businessId))
            return false;
        if (businessName == null) {
            if (other.businessName != null)
                return false;
        } else if (!businessName.equals(other.businessName))
            return false;
        if (businessPhoneNum == null) {
            if (other.businessPhoneNum != null)
                return false;
        } else if (!businessPhoneNum.equals(other.businessPhoneNum))
            return false;
        return true;
    }

}
