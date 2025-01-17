/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
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

package org.kie.kogito.jobs.management.quarkus;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.kie.kogito.Application;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.Processes;
import org.kie.services.jobs.impl.TriggerJobCommand;

@Path("/management/jobs")
public class CallbackJobsServiceResource {

    @Inject
    Processes processes;

    @Inject
    Application application;

    @POST
    @Path("{processId}/instances/{processInstanceId}/timers/{timerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response triggerTimer(@PathParam("processId") String processId,
            @PathParam("processInstanceId") String processInstanceId,
            @PathParam("timerId") String timerId,
            @QueryParam("limit") @DefaultValue("0") Integer limit) {
        if (processId == null || processInstanceId == null) {
            return Response.status(Status.BAD_REQUEST).entity("Process id and Process instance id must be given").build();
        }

        Process<?> process = processes.processById(processId);
        if (process == null) {
            return Response.status(Status.NOT_FOUND).entity("Process with id " + processId + " not found").build();
        }

        return new TriggerJobCommand(processInstanceId, timerId, limit, process, application.unitOfWorkManager()).execute()
                ? Response.status(Status.OK).build()
                : Response.status(Status.NOT_FOUND).entity("Process instance with id " + processInstanceId + " not found").build();

    }
}
