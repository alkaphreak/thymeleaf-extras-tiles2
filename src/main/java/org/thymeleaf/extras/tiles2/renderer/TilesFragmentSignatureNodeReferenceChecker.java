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
package org.thymeleaf.extras.tiles2.renderer;

import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Attribute;
import org.thymeleaf.dom.DOMSelector;
import org.thymeleaf.dom.NestableAttributeHolderNode;
import org.thymeleaf.dom.Node;
import org.thymeleaf.standard.expression.FragmentSignature;
import org.thymeleaf.standard.expression.StandardExpressionProcessor;
import org.thymeleaf.util.Validate;


/**
 * <p>
 *   Implementation of the {@link org.thymeleaf.dom.DOMSelector.INodeReferenceChecker} interface used for looking
 *   for Tiles <i>fragment signature</i> attributes in nodes, and consider the names of the fragments to be
 *   <i>reference values</i>.
 * </p>
 * <p>
 *   For example, if the Tiles fragment signature attribute is <tt>tiles:fragment</tt> (which is the default), objects
 *   of this class will consider that DOM Selector expressions like <tt>myfrag</tt> or <tt>%myfrag</tt> will match
 *   <tt>tiles:fragment="myfrag(param1, param2)"</tt>.
 * </p>
 * 
 * @author Daniel Fern&aacute;ndez
 * 
 * @since 2.1.0
 *
 */
public final class TilesFragmentSignatureNodeReferenceChecker extends DOMSelector.AbstractNodeReferenceChecker {

    private final Configuration configuration;
    private final String dialectPrefix;
    private final String fragmentAttributeName;

    public TilesFragmentSignatureNodeReferenceChecker(
            final Configuration configuration, final String dialectPrefix, final String fragmentAttributeName) {
        super();
        Validate.notNull(configuration, "Configuration cannot be null");
        Validate.notNull(fragmentAttributeName, "Fragment attribute name cannot be null");
        this.configuration = configuration;
        this.dialectPrefix = dialectPrefix;
        this.fragmentAttributeName = Attribute.normalizeAttributeName(fragmentAttributeName);
    }


    public Configuration getConfiguration() {
        return this.configuration;
    }

    public String getDialectPrefix() {
        return this.dialectPrefix;
    }

    public String getFragmentAttributeName() {
        return this.fragmentAttributeName;
    }


    public boolean checkReference(final Node node, final String referenceValue) {

        if (node instanceof NestableAttributeHolderNode) {

            final NestableAttributeHolderNode attributeHolderNode = (NestableAttributeHolderNode) node;

            if (attributeHolderNode.hasNormalizedAttribute(this.dialectPrefix, this.fragmentAttributeName)) {
                final String elementAttrValue =
                        attributeHolderNode.getAttributeValueFromNormalizedName(this.dialectPrefix, this.fragmentAttributeName);
                if (elementAttrValue != null) {
                    final FragmentSignature fragmentSignature =
                            StandardExpressionProcessor.parseFragmentSignature(this.configuration, elementAttrValue);
                    if (fragmentSignature != null) {
                        final String signatureFragmentName = fragmentSignature.getFragmentName();
                        if (referenceValue.equals(signatureFragmentName)) {
                            return true;
                        }
                    }
                }
            }

        }

        return false;

    }


}
