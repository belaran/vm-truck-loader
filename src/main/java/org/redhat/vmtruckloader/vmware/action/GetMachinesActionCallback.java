/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.redhat.vmtruckloader.vmware.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.redhat.vmtruckloader.vmware.VMWareManagedObjectUtils;

import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;


/**
 * Simple VMWare action class that executes a finds all VMs.
 * <p/>
 * Note that these action classes are not designed to be thread safe.
 *  
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 * @author Romain Pelisse - romain@redhat.com
 */
public class GetMachinesActionCallback extends AbstractVMWareActionCallback<Collection<VirtualMachine>> {
	
	@Override
	public Collection<VirtualMachine> doInVmware(ServiceInstance serviceInstance) {
		return getVms(serviceInstance);
	}
	
	private List<VirtualMachine> getVms(ServiceInstance serviceInstance) {
		final List<VirtualMachine> virtualMachines = new ArrayList<VirtualMachine>();

		final Folder rootFolder = serviceInstance.getRootFolder();
		final InventoryNavigator inventoryNavigator = new InventoryNavigator(rootFolder);

		for (ManagedEntity managedEntity : VMWareManagedObjectUtils.getManagedEntities(inventoryNavigator, VirtualMachine.class.getName())) {
			virtualMachines.add((VirtualMachine) managedEntity);
		}
		return virtualMachines;
	}

}
