declare function ns2:min-non-empty-string
($stringSeq as xs:string*) as xs:string? {
   min($stringSeq[. != ''])
};
declare function ns2:max-string ($stringSeq as xs:string*) as xs:string?{
   max($stringSeq)
};
