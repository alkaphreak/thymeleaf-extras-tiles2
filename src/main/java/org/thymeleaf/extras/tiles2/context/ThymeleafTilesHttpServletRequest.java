/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2012, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.extras.tiles2.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.util.Validate;



/**
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
public class ThymeleafTilesHttpServletRequest extends HttpServletRequestWrapper {

    private final TemplateEngine templateEngine;
    private final IProcessingContext processingContext;
    
    
    
    public ThymeleafTilesHttpServletRequest(final HttpServletRequest request,
            final TemplateEngine templateEngine, final IProcessingContext processingContext) {
        super(request);
        Validate.notNull(templateEngine, "Template Engine cannot be null");
        Validate.notNull(processingContext, "Processing Context cannot be null");
        this.templateEngine = templateEngine;
        this.processingContext = processingContext;
    }



    
    public TemplateEngine getTemplateEngine() {
        return this.templateEngine;
    }



    public IProcessingContext getProcessingContext() {
        return this.processingContext;
    }


    
}
