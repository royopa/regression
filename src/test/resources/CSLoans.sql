SELECT MAX(case when element_type = 'FC_AGENT_ID' then element_value end) AS FC_AGENT_ID
,MAX(case when element_type = 'FC_ISSUE_AMOUNT' then element_value end) AS FC_ISSUE_AMOUNT
,convert(identity_value, unsigned integer) AS identity_value
,MAX(case when element_type = 'FC_DEAL_ID' then element_value end) AS FC_DEAL_ID
,MAX(case when element_type = 'FC_PERM_ID' then element_value end) AS FC_PERM_ID
,MAX(case when element_type = 'FC_MARKET_ISSUANCE' then element_value end) AS FC_MARKET_ISSUANCE
,MAX(case when element_type = 'FC_COMPANY' then element_value end) AS FC_COMPANY
,MAX(case when element_type = 'FC_LAUNCH_DATE' then element_value end) AS FC_LAUNCH_DATE
,MAX(case when element_type = 'FC_CURR' then element_value end) AS FC_CURR
,MAX(case when element_type = 'FC_ISSUE_AMOUNT' then element_value end) AS FC_ISSUE_AMOUNT
,MAX(case when element_type = 'FC_TRANSACTION_TYPE' then element_value end) AS FC_TRANSACTION_TYPE
,MAX(case when element_type = 'FC_EQUITY_OWNERSHIP' then element_value end) AS FC_EQUITY_OWNERSHIP
,MAX(case when element_type = 'FC_CS_MKT_SECTOR' then element_value end) AS FC_CS_MKT_SECTOR
,MAX(case when element_type = 'FC_CS_COUNTRY' then element_value end) AS FC_CS_COUNTRY
,MAX(case when element_type = 'FC_CS_ISSUER_NAME' then element_value end) AS FC_CS_ISSUER_NAME
,MAX(case when element_type = 'FC_ISSUE_AMOUNT_EURO' then element_value end) AS FC_ISSUE_AMOUNT_EURO
,MAX(case when element_type = 'FC_ISSUE_AMOUNT_USD' then element_value end) AS FC_ISSUE_AMOUNT_USD
,MAX(case when element_type = 'FC_1L_LEV' then element_value end) AS FC_1L_LEV
,MAX(case when element_type = 'FC_2L_LEV' then element_value end) AS FC_2L_LEV
,MAX(case when element_type = 'FC_TTL_LEV' then element_value end) AS FC_TTL_LEV
,MAX(case when element_type = 'FC_SPCL_FEATURES' then element_value end) AS FC_SPCL_FEATURES
,MAX(case when element_type = 'FC_MEETING_DT' then element_value end) AS FC_MEETING_DT
,MAX(case when element_type = 'FC_COMMIT_DT' then element_value end) AS FC_COMMIT_DT
,MAX(case when element_type = 'FC_CLOSING_DT' then element_value end) AS FC_CLOSING_DT
,MAX(case when element_type = 'FC_TERM_LOANB' then element_value end) AS FC_TERM_LOANB
,MAX(case when element_type = 'FC_TLB_CCY' then element_value end) AS FC_TLB_CCY
,MAX(case when element_type = 'FC_TLB_ISSUE_AMOUNT' then element_value end) AS FC_TLB_ISSUE_AMOUNT
,MAX(case when element_type = 'FC_TLB_RATE' then element_value end) AS FC_TLB_RATE
,MAX(case when element_type = 'FC_TLB_MARGIN' then element_value end) AS FC_TLB_MARGIN
,MAX(case when element_type = 'FC_TLB_FLOOR' then element_value end) AS FC_TLB_FLOOR
,MAX(case when element_type = 'FC_TLB_OID' then element_value end) AS FC_TLB_OID
,MAX(case when element_type = 'FC_TLB_TERM' then element_value end) AS FC_TLB_TERM
,MAX(case when element_type = 'FC_TLB_PAYMENT' then element_value end) AS FC_TLB_PAYMENT
,MAX(case when element_type = 'FC_TLB_EURO' then element_value end) AS FC_TLB_EURO
,MAX(case when element_type = 'FC_TENOR' then element_value end) AS FC_TENOR
,MAX(case when element_type = 'FC_3MNTH_LIBOR' then element_value end) AS FC_3MNTH_LIBOR
,MAX(case when element_type = 'FC_YTM' then element_value end) AS FC_YTM
,MAX(case when element_type = 'FC_PRICE_TALK' then element_value end) AS FC_PRICE_TALK
,MAX(case when element_type = 'FC_2L' then element_value end) AS FC_2L
,MAX(case when element_type = 'FC_2L_CCY' then element_value end) AS FC_2L_CCY
,MAX(case when element_type = 'FC_2L_ISSUE_AMOUNT' then element_value end) AS FC_2L_ISSUE_AMOUNT
,MAX(case when element_type = 'FC_2L_RATE' then element_value end) AS FC_2L_RATE
,MAX(case when element_type = 'FC_2L_MARGIN' then element_value end) AS FC_2L_MARGIN
,MAX(case when element_type = 'FC_2L_FLOOR' then element_value end) AS FC_2L_FLOOR
,MAX(case when element_type = 'FC_2LOID' then element_value end) AS FC_2LOID
,MAX(case when element_type = 'FC_2L_TERM' then element_value end) AS FC_2L_TERM
,MAX(case when element_type = 'FC_2L_PAYMENT' then element_value end) AS FC_2L_PAYMENT
,MAX(case when element_type = 'FC_2L_EURO' then element_value end) AS FC_2L_EURO
FROM transaction_element te
  JOIN tranche t ON t.id = te.entity_id
  JOIN entity_cross_reference ecr ON ecr.entity_id = t.transaction_id
WHERE te.information_provenance = 'capstr' and te.entity_type = 'Tranche' and category = 'Loan public data'
GROUP BY te.entity_id
ORDER BY convert(identity_value, unsigned integer);