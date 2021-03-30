package com.github.philippheuer.gcf4j.core.util;

import com.github.philippheuer.gcf4j.api.domain.IGCFRole;
import com.github.philippheuer.gcf4j.core.domain.GCFRole;

import java.util.Set;

/**
 * GCF Default Roles / RoleSets
 */
public class GCFDefaultRoles {

    public static IGCFRole ROLE_INSTANCE_ADMIN = GCFRole.builder().scope("instance.system").id("admin").name("Instance Administrator").build();

    public static IGCFRole ROLE_INSTANCE_OWNER = GCFRole.builder().scope("instance.system").id("owner").name("Instance Owner").build();

    public static IGCFRole ROLE_SUPERADMIN = GCFRole.builder().id("superadmin").scope("global").build();

    public static Set<IGCFRole> ROLESET_OWNER = Set.of(ROLE_INSTANCE_OWNER, ROLE_SUPERADMIN);

    public static Set<IGCFRole> ROLESET_ADMINISTRATION = Set.of(ROLE_INSTANCE_ADMIN, ROLE_INSTANCE_OWNER, ROLE_SUPERADMIN);

}
