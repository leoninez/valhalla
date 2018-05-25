grammar SearchQuery;
//[@site="[SITEPARAM]"]{@jobId,@jobDefId,@jobName,@currentState,@user,@queue,@startTime,@endTime,@jobType}&pageSize=[PAGESIZEPARAM]&startTime=[STARTTIMEPARAM]&endTime=[ENDTIMEPARAM]
//JobProcessTimeStampService[@site="SITEPARAM"]<@site>{max(currentTimeStamp)}

query : clzName (filter)? (sort)? (selector)? (aggregator)? (search_max_size)?;
//query : value;

clzName : KEY;

filter : '['filter_list']' | '[]';
filter_list : (filter_item','filter_list) | filter_item;
filter_item : filter_item_equal | filter_item_range | filter_item_time_range | filter_item_compare;
//filter_item : filter_item_equal;
filter_item_equal : '@'KEY'='value;
filter_item_range : '@'KEY'=['NUM','NUM']';
filter_item_time_range : '@'KEY'=['DATEKEY','DATEKEY']';
filter_item_compare : '@'KEY sort_item_order NUM;

sort : '('sort_list')' | '()';
sort_list : (sort_item','sort_list) | sort_item;
sort_item : '@'KEY (sort_item_order)?;
sort_item_order : '<' | '>';

selector : '{'selector_list'}' | '{}';
selector_list : (selector_item','selector_list) | selector_item;
selector_item : '@'KEY;

//aggregator : aggregator_key aggregator_target;
//aggregator_key : '<@'KEY'>';
//aggregator_target : '{'aggregator_simple_target'('KEY')}';
//aggregator_simple_target : 'max' | 'avg' | 'min' | 'top';

aggregator : '<'aggregator_list'>' | '<>';
aggregator_list : (aggregator_item','aggregator_list) | aggregator_item;
aggregator_item : aggregator_term_item | aggregator_top_item | aggregator_stat_item | aggregator_stat_list |aggregator_nested_item | aggregator_histo_item;

aggregator_term_item : '@'KEY | '@'(KEY','NUM);

aggregator_top_item : 'top('KEY','NUM','aggregator_top_order')' | 'top('KEY','NUM')';
aggregator_top_order : 'desc' | 'asc';
//aggregator_stat_item : aggregator_stat_action'('key_list')';      // TODO,support multi keys

aggregator_stat_list : '['aggregator_stat_item_list']';
aggregator_stat_item_list : aggregator_stat_item','aggregator_stat_item_list | aggregator_stat_item;
aggregator_stat_item : aggregator_stat_action'('KEY')';
aggregator_stat_action : 'max' | 'avg' | 'min' | 'sum';

aggregator_nested_item: '$'KEY;

aggregator_histo_item: 'histo('KEY','NUM')';

search_max_size : '@'NUM;

key_list : KEY','key_list | KEY;
value: NUM | STR | BOOL;

BOOL : 'true'|'false';
DATEKEY : '201'[0-9]'-'[0-9][0-9]'-'[0-9][0-9]'_'[0-9][0-9]':'[0-9][0-9]':'[0-9][0-9];
STR : '"'[_\-a-zA-Z0-9. ]+'"';
KEY : [_a-zA-Z][_a-zA-Z0-9.]*;
NUM : [0-9]+ ;
