/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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
package org.kie.kogito.serverless.workflow.utils;

import java.util.Properties;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkflowAppContextTest {

    private static Properties testWorkflowProperties() {
        Properties properties = new Properties();
        properties.put("kogito.sw.functions.testfunction1.testprop1", "testprop1val");
        properties.put("kogito.sw.functions.testfunction1.testprop2", "testprop2val");
        properties.put("kogito.sw.functions.testfunction2.testprop1", "testprop1val");
        properties.put("kogito.sw.functions.testfunction2.testprop2", "testprop2val");
        properties.put("kogito.sw.functions.testfunction3.ruleflowgroup", "testruleflowgroup");

        return properties;
    }

    @Test
    public void testOfAppResources() throws Exception {
        WorkflowAppContext workflowAppContext = WorkflowAppContext.ofAppResources();
        assertThat(workflowAppContext).isNotNull();
        assertThat(workflowAppContext.getApplicationProperties()).isNotNull();
        assertThat(workflowAppContext.getApplicationProperties().getProperty("kogito.sw.functions.testfunction1.testprop1")).isEqualTo("testprop1val");
        assertThat(workflowAppContext.getApplicationProperties().getProperty("kogito.sw.functions.testfunction1.testprop2")).isEqualTo("testprop2val");
        assertThat(workflowAppContext.getApplicationProperties().getProperty("kogito.sw.functions.testfunction2.testprop1")).isEqualTo("testprop1val");
        assertThat(workflowAppContext.getApplicationProperties().getProperty("kogito.sw.functions.testfunction2.testprop2")).isEqualTo("testprop2val");
    }

    @Test
    public void testOfProperties() throws Exception {
        WorkflowAppContext workflowAppContext = WorkflowAppContext.ofProperties(testWorkflowProperties());
        assertThat(workflowAppContext).isNotNull();
        assertThat(workflowAppContext.getApplicationProperties()).isNotNull();
        assertThat(workflowAppContext.getApplicationProperties().getProperty("kogito.sw.functions.testfunction1.testprop1")).isEqualTo("testprop1val");
        assertThat(workflowAppContext.getApplicationProperties().getProperty("kogito.sw.functions.testfunction1.testprop2")).isEqualTo("testprop2val");
        assertThat(workflowAppContext.getApplicationProperties().getProperty("kogito.sw.functions.testfunction2.testprop1")).isEqualTo("testprop1val");
        assertThat(workflowAppContext.getApplicationProperties().getProperty("kogito.sw.functions.testfunction2.testprop2")).isEqualTo("testprop2val");
    }

}
