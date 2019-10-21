This is a demo of the pact specification V2 and V3
compliance in contract files produced from
pact-jvm-consumer-junit. While the
PactSpecVersion.V2 argument in

    @Rule
    public PactProviderRule provider = new PactProviderRule("RandomIntService", "localhost", 8123, PactSpecVersion.V2,this);

does change the produced contract file (found under target/log)
compared to the V3-version, the V2 version still contains the same
"match": "integer" which is not, to my knowledge, compliant with
the V2 Pact specification.

In the directory contractfiles there are three contract files.

PactTestV2-RandomIntService.json
PactTestV3-RandomIntService.json
PactTestV2-RandomIntService_manual_adjustments.json

Two first is as produced by respectively program. Third is based on the
V2-contract by "integer" has been changed to "type". That is the only
contract that can be verified by pact-provider-verifier, like this:

pact-provider-verifier  PactTestV2-RandomIntService_manual_adjustments.json --provider-base-url http://www2.freefarm.se

the other:
pact-provider-verifier   PactTestV2-RandomIntService.json --provider-base-url http://coltat.telia.net:8401
pact-provider-verifier   PactTestV3-RandomIntService.json --provider-base-url http://coltat.telia.net:8401

fails. For example PactTestV2-RandomIntService.json fails like this:

INFO: Reading pact at PactTestV2-RandomIntService.json
WARN: Ignoring unsupported matching rules {"match"=>"integer"} for path $['body']['randomint']
has status code 200
has a matching body (FAILED - 1)
includes headers
    "Content-Type" which matches /application\/json(;\s?charset=[\w\-]+)?/

Seems like the contract file using the PactSpecVersion.V2 argument to PactProviderRule
simply do not produce a V2 compliant file. Or pact-provider-verifier does not support V2?