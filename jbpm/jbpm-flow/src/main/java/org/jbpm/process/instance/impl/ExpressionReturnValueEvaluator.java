/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.process.instance.impl;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.process.workitems.impl.expr.ExpressionHandlerFactory;
import org.kie.kogito.process.workitems.impl.expr.ParsedExpression;

public class ExpressionReturnValueEvaluator implements ReturnValueEvaluator {
    private ParsedExpression expression;
    private String rootName;

    public ExpressionReturnValueEvaluator(String lang, String expression, String rootName) {
        this.expression = ExpressionHandlerFactory.get(lang).parse(expression);
        this.rootName = rootName;
    }

    @Override
    public Object evaluate(KogitoProcessContext processContext) throws Exception {
        return expression.eval(rootName != null ? processContext.getVariable(rootName) : processContext, Boolean.class);
    }

}
