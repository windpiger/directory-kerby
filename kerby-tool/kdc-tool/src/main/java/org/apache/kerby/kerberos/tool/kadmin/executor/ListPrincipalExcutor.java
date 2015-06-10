/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.kerby.kerberos.tool.kadmin.executor;

import org.apache.kerby.config.Config;
import org.apache.kerby.kerberos.kerb.KrbException;
import org.apache.kerby.kerberos.kerb.admin.Kadmin;

import java.util.List;

public class ListPrincipalExcutor implements KadminCommandExecutor {
    private Config backenConfig;

    public ListPrincipalExcutor(Config backenConfig) {
        this.backenConfig = backenConfig;
    }

    @Override
    public void execute(String input) {
        String[] commands = input.split(" ");
        List<String> principalNames = null;


        if (commands.length == 1) {
            Kadmin kadmin = new Kadmin(backenConfig);
            try {
                principalNames = kadmin.listPrincipal();
            } catch (KrbException e) {
                System.err.print("Fail to list principal!" + e.getMessage());
            }
        }
        System.out.println("Principals are listed:");

        for (String principalName : principalNames) {
            System.out.println(principalName);
        }
    }
}
