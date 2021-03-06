// Copyright 2017, Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.adwords.scripts.solutions.linkchecker;

import com.google.adwords.scripts.solutions.linkchecker.service.JobsCleanupService;
import com.google.adwords.scripts.solutions.linkchecker.service.SharedKeyService;
import com.google.inject.Inject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to respond to the requests from the AppEngine cron and clean up old BatchOperation
 * entries.
 */
public class CronServlet extends HttpServlet {
  private final JobsCleanupService jobsCleanupService;

  @Inject
  public CronServlet(JobsCleanupService jobsCleanupService, SharedKeyService sharedKeyService) {
    this.jobsCleanupService = jobsCleanupService;
    sharedKeyService.getKey();
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    jobsCleanupService.cleanup();
  }
}
