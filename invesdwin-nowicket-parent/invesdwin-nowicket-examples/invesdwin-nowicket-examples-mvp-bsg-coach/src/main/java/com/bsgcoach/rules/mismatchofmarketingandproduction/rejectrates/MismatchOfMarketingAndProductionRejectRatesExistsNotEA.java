package com.bsgcoach.rules.mismatchofmarketingandproduction.rejectrates;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import com.bsgcoach.web.request.CompanyRegion;

@Named
@Immutable
public class MismatchOfMarketingAndProductionRejectRatesExistsNotEA extends
        AMismatchOfMarketingAndProductionRejectRatesExistsNot {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.EuropeAfrica;
    }

}
