package com.cumpleanos.common.records;

public record CompanyParametersRecord(
        Long companyId,

        String primaryColor,
        String secondaryColor,
        String lightColor,
        String darkColor,
        String primaryTextColor,
        String secondaryTextColor,
        String titleStyle,
        String subtitleStyle,

        String primaryLogo,
        String secondaryLogo,

        String privacyPolicy
) {
}
