/**
 * author @bhupendrasambare
 * Date   :07/04/26
 * Time   :12:21 am
 * Project:Keep
 **/
package com.service.keep.domain.port.outbound;

import com.service.keep.application.dto.response.MetadataResponse;
import com.service.keep.application.dto.response.SearchQueryResponse;

public interface AiSearchPort {

    MetadataResponse generateMetadata(String title, String description);

    SearchQueryResponse parseSearchQuery(String prompt);
}
